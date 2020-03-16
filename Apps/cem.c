// This file is part of InK.

/*
 * InK is free software: you ca    n redistribute it and/or modify
 * it under the terms of the GNU General Public License as published *
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


#include "ink.h"
#include <stdlib.h>


// Define constant persistent variables that are stored in non-volatile memory.
#define NIL 0
#define DICT_SIZE 512
#define BLOCK_SIZE 64
#define NUM_LETTERS_IN_SAMPLE 2
#define LETTER_MASK 0x00FF
#define LETTER_SIZE_BITS 8
#define NUM_LETTERS (LETTER_MASK + 1)


// Define task-shared persistent variables.
__shared(
    uint32_t pinCont;
    uint32_t v_letter_idx;
    uint32_t v_letter;
    uint32_t v_prev_sample;
    uint32_t v_out_len;
    uint32_t v_node_count;
    uint32_t v_sample;
    uint32_t v_sample_count;
    uint32_t v_sibling;
    uint32_t v_child;
    uint32_t v_parent;
    uint32_t v_parent_next;
    uint32_t v_parent_node_letter;
    uint32_t v_parent_node_sibling;
    uint32_t v_parent_node_child;
    uint32_t v_sibling_node_letter;
    uint32_t v_sibling_node_sibling;
    uint32_t v_sibling_node_child;
    uint32_t v_symbol;
    uint32_t v_compressed_data_letter[ BLOCK_SIZE ];
    uint32_t v_compressed_data_sibling[ BLOCK_SIZE ];
    uint32_t v_compressed_data_child[ BLOCK_SIZE ];
    uint32_t v_dict_letter[ DICT_SIZE ];
    uint32_t v_dict_sibling[ DICT_SIZE ];
    uint32_t v_dict_child[ DICT_SIZE ];
)


// Declare tasks that will be implemented
ENTRY_TASK(t_init);
TASK(task_init_dict);
TASK(task_sample);
TASK(task_measure_temp);
TASK(task_letterize);
TASK(task_compress);
TASK(task_find_sibling);
TASK(task_add_node);
TASK(task_add_insert);
TASK(task_append_compressed);
TASK(task_print);
TASK(task_done);


// Called at the very first boot
void thread1_init(){
    // create a thread with priority 15 and entry task t_init
    __CREATE(15, t_init);
    __SIGNAL(15);
}


// Define helper functions
int acquire_sample (uint32_t prev_sample) {
    uint32_t sample = (prev_sample + 1) & 0x03;
    
    return sample;
}


// Implementation of all tasks that are declared above
ENTRY_TASK(t_init) {
    __SET(pinCont, 1);
    __SET(v_parent_next, 0);
    __SET(v_out_len, 0);
    __SET(v_letter, 0);
    __SET(v_prev_sample, 0);
    __SET(v_letter_idx, 0);
    __SET(v_sample_count, 1);
    return task_init_dict;
}

TASK(task_init_dict) {
    uint32_t i = __GET(v_letter);
    __SET(v_dict_letter[ i ], i);
    __SET(v_dict_sibling[ i ], NIL);
    __SET(v_dict_child[ i ], NIL);
    __SET(v_letter, __GET(v_letter) + 1);
    if(i < NUM_LETTERS) {
        return task_init_dict;
    } else {
        __SET(v_node_count, NUM_LETTERS);
        return task_sample;
    }
}

TASK(task_sample) {
    uint32_t letter_idx = __GET(v_letter_idx);
    uint32_t next_v_letter_idx = letter_idx + 1;
    if(next_v_letter_idx == NUM_LETTERS_IN_SAMPLE) {
        next_v_letter_idx = 0;
    } 
    if(letter_idx == 0) {
        __SET(v_letter_idx, next_v_letter_idx);
        return task_measure_temp;
    } else {
        __SET(v_letter_idx, next_v_letter_idx);
        return task_letterize;
    }
}

TASK(task_measure_temp) {
    uint32_t prev_sample = __GET(v_prev_sample);
    uint32_t sample = acquire_sample(prev_sample);
    prev_sample = sample;
    __SET(v_prev_sample, prev_sample);
    __SET(v_sample, sample);
    return task_letterize;
}

TASK(task_letterize) {
    uint32_t letter_idx = __GET(v_letter_idx);
    uint32_t letter_shift;
    uint32_t letter;
    if(letter_idx == 0) {
        letter_idx = NUM_LETTERS_IN_SAMPLE;
    } else {
        letter_idx = letter_idx - 1;
    }
    letter_shift = LETTER_SIZE_BITS * letter_idx;
    letter = (__GET(v_sample) & (LETTER_MASK << letter_shift)) >> letter_shift;
    __SET(v_letter, letter);
    return task_compress;
}

TASK(task_compress) {
    uint32_t parent = __GET(v_parent_next);
    uint32_t __cry;
    __cry = __GET(v_dict_child[ parent ]);
    __SET(v_sibling, __cry);
    __cry = __GET(v_dict_letter[ parent ]);
    __SET(v_parent_node_letter, __cry);
    __cry = __GET(v_dict_sibling[ parent ]);
    __SET(v_parent_node_sibling, __cry);
    __cry = __GET(v_dict_child[ parent ]);
    __SET(v_parent_node_child, __cry);
    __SET(v_parent, parent);
    __cry = __GET(v_dict_child[ parent ]);
    __SET(v_child, __cry);
    __SET(v_sample_count, __GET(v_sample_count) + 1);
    return task_find_sibling;
}

TASK(task_find_sibling) {
    uint32_t i = __GET(v_sibling);
    uint32_t __cry;
    uint32_t starting_node_idx;
    if(i != NIL) {
        __cry = __GET(v_letter);
        if(__GET(v_dict_letter[ i ]) == __cry) {
            __cry = __GET(v_sibling);
            __SET(v_parent_next, __cry);
            return task_letterize;
        } else if (__GET(v_dict_sibling[ i ]) != 0) {
            __cry = __GET(v_dict_sibling[ i ]);
            __SET(v_sibling, __cry);
            return task_find_sibling;
        } else {
            starting_node_idx = __GET(v_letter);
            __SET(v_parent_next, starting_node_idx);
            if(__GET(v_child) == NIL) {
                return task_add_insert;
            } else {
                return task_add_node;
            }
        }
    } else {
        starting_node_idx = __GET(v_letter);
        __SET(v_parent_next, starting_node_idx);
        if(__GET(v_child) == NIL) {
            return task_add_insert;
        } else {
            return task_add_node;
        }
    }
}

TASK(task_add_node) {
    uint32_t i = __GET(v_sibling);
    uint32_t next_sibling;
    uint32_t __cry;
    if(__GET(v_dict_sibling[ i ]) != NIL) {
        next_sibling = __GET(v_dict_sibling[ i ]);
        __SET(v_sibling, next_sibling);
        return task_add_node;
    } else {
        __cry = __GET(v_dict_letter[ i ]);
        __SET(v_sibling_node_letter, __cry);
        __cry = __GET(v_dict_sibling[ i ]);
        __SET(v_sibling_node_sibling, __cry);
        __cry = __GET(v_dict_child[ i ]);
        __SET(v_sibling_node_child, __cry);
        return task_add_insert;
    }
}

TASK(task_add_insert) {
    uint32_t child;
    uint32_t __cry;
    uint32_t i;
    uint32_t last_sibling;
    if(__GET(v_node_count) == DICT_SIZE) {
        
        while(1 == 1) {
        }
    } 
    child = __GET(v_node_count);
    if(__GET(v_parent_node_child) == NIL) {
        __SET(v_parent_node_child, child);
        i = __GET(v_parent);
        __cry = __GET(v_parent_node_letter);
        __SET(v_dict_letter[ i ], __cry);
        __cry = __GET(v_parent_node_sibling);
        __SET(v_dict_sibling[ i ], __cry);
        __cry = __GET(v_parent_node_child);
        __SET(v_dict_child[ i ], __cry);
    } else {
        last_sibling = __GET(v_sibling);
        __SET(v_sibling_node_sibling, child);
        __cry = __GET(v_sibling_node_letter);
        __SET(v_dict_letter[ last_sibling ], __cry);
        __cry = __GET(v_sibling_node_sibling);
        __SET(v_dict_sibling[ last_sibling ], __cry);
        __cry = __GET(v_sibling_node_child);
        __SET(v_dict_child[ last_sibling ], __cry);
    }
    __cry = __GET(v_letter);
    __SET(v_dict_letter[ child ], __cry);
    __SET(v_dict_sibling[ child ], NIL);
    __SET(v_dict_child[ child ], NIL);
    __cry = __GET(v_parent);
    __SET(v_symbol, __cry);
    __SET(v_node_count, __GET(v_node_count) + 1);
    return task_append_compressed;
}

TASK(task_append_compressed) {
    uint32_t __cry = __GET(v_symbol);
    uint32_t i = __GET(v_out_len);
    __SET(v_compressed_data_letter[ i ], __cry);
    __SET(v_out_len, __GET(v_out_len) + 1);
    if(__GET(v_out_len) == BLOCK_SIZE) {
        return task_print;
    } else {
        return task_sample;
    }
}

TASK(task_print) {
    uint32_t i;
    uint32_t index;
    
    for (i = 0; i < BLOCK_SIZE; i = i + 1) {
        index = __GET(v_compressed_data_letter[ i ]);
    }
    return task_done;
}

TASK(task_done) {
    return NULL;
}

