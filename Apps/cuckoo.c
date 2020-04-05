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
#include <stdbool.h>


// Define constant persistent variables that are stored in non-volatile memory.
#define BYTES_IN_INTEGER 4
#define NUM_BUCKETS 128
#define NUM_INSERTS NUM_BUCKETS / 4
#define NUM_LOOKUPS NUM_INSERTS
#define MAX_RELOCATIONS 8
#define BUFFER_SIZE 32
#define TASK_NUM 15
#define task_index_t_init 1
#define task_index_t_init_array 2
#define task_index_t_generate_key 3
#define task_index_t_calc_indexes 4
#define task_index_t_calc_indexes_index_1 5
#define task_index_t_calc_indexes_index_2 6
#define task_index_t_insert 7
#define task_index_t_add 8
#define task_index_t_relocate 9
#define task_index_t_insert_done 10
#define task_index_t_lookup 11
#define task_index_t_lookup_search 12
#define task_index_t_lookup_done 13
#define task_index_t_print_stats 14
#define task_index_t_done 15
#define init_key 0x0001


// Define task-shared persistent variables.
__shared(
    uint32_t pinRaised;
    uint32_t _v_filter[ NUM_BUCKETS ];
    uint32_t _v_index;
    uint32_t _v_key;
    uint32_t _v_next_task;
    uint32_t _v_fingerprint;
    uint32_t _v_index1;
    uint32_t _v_index2;
    uint32_t _v_relocation_count;
    uint32_t _v_insert_count;
    uint32_t _v_inserted_count;
    uint32_t _v_lookup_count;
    uint32_t _v_member_count;
    bool _v_success;
    bool _v_member;
)


// Declare tasks that will be implemented
ENTRY_TASK(task_init);
TASK(task_init_array);
TASK(task_generate_key);
TASK(task_calc_indexes);
TASK(task_calc_indexes_index_1);
TASK(task_calc_indexes_index_2);
TASK(task_insert);
TASK(task_add);
TASK(task_relocate);
TASK(task_insert_done);
TASK(task_lookup);
TASK(task_lookup_search);
TASK(task_lookup_done);
TASK(task_print_stats);
TASK(task_done);


// Called at the very first boot
void thread1_init(){
    // create a thread with priority 15 and entry task task_init
    __CREATE(15, task_init);
    __SIGNAL(15);
}


// Define helper functions
int djb_hash (uint32_t data, uint32_t len) {
    uint32_t i;
    uint32_t hash = 5381;
    uint32_t byte_1 = data >> 24;
    uint32_t byte_2 = data << 8 >> 24;
    uint32_t byte_3 = data << 16 >> 24;
    uint32_t byte_4 = data << 24 >> 24;
    uint32_t bytes[ 4 ] = {byte_1, byte_2, byte_3, byte_4};
    
    for (i = 0; i < len; i = i + 1) {
        hash = ((hash << 5) + hash) + bytes[ i ];
    }
    hash = hash & 0xFFFF;
    
    return hash;
}

int hash_to_index (uint32_t fp) {
    uint32_t hash = djb_hash(fp, BYTES_IN_INTEGER);
    hash = hash & (NUM_BUCKETS - 1);
    
    return hash;
}

int hash_to_fingerprint (uint32_t key) {
    uint32_t hash = djb_hash(key, BYTES_IN_INTEGER);
    
    return hash;
}


// Implementation of all tasks that are declared above
ENTRY_TASK(task_init) {
    uint32_t i;
    
    for (i = 0; i < NUM_BUCKETS; i = i + 1) {
        __SET(_v_filter[ i ], 0);
    }
    __SET(_v_insert_count, 0);
    __SET(_v_lookup_count, 0);
    __SET(_v_inserted_count, 0);
    __SET(_v_member_count, 0);
    __SET(_v_key, init_key);
    __SET(_v_next_task, task_index_t_insert);
    return task_generate_key;
}

TASK(task_init_array) {
    uint32_t i;
    uint32_t index;
    
    for (i = 0; i < BUFFER_SIZE - 1; i = i + 1) {
        index = i + __GET(_v_index) * (BUFFER_SIZE - 1);
        __SET(_v_filter[ index ], 0);
    }
    __SET(_v_index, __GET(_v_index) + __GET(_v_index));
    if(__GET(_v_index) == NUM_BUCKETS / (BUFFER_SIZE - 1)) {
        return task_generate_key;
    } else {
        return task_init_array;
    }
}

TASK(task_generate_key) {
    uint32_t __cry;
    uint32_t next_task_index;
    uint32_t next_task = __GET(_v_next_task);
    __cry = (__GET(_v_key) + 1) * 17;
    __SET(_v_key, __cry);
    if(next_task >= task_index_t_generate_key) {
        next_task_index = next_task - task_index_t_generate_key;
    } else {
        next_task_index = TASK_NUM - next_task + task_index_t_generate_key;
    }
    if(next_task_index == 0) {
        return task_generate_key;
    } else if (next_task_index == 1) {
        return task_calc_indexes;
    } else if (next_task_index == 2) {
        return task_calc_indexes_index_1;
    } else if (next_task_index == 3) {
        return task_calc_indexes_index_2;
    } else if (next_task_index == 4) {
        return task_insert;
    } else if (next_task_index == 5) {
        return task_add;
    } else if (next_task_index == 6) {
        return task_relocate;
    } else if (next_task_index == 7) {
        return task_insert_done;
    } else if (next_task_index == 8) {
        return task_lookup;
    } else if (next_task_index == 9) {
        return task_lookup_search;
    } else if (next_task_index == 10) {
        return task_lookup_done;
    } else if (next_task_index == 11) {
        return task_print_stats;
    } else if (next_task_index == 12) {
        return task_done;
    } else if (next_task_index == 13) {
        return task_init;
    } else if (next_task_index == 14) {
        return task_init_array;
    } else if (next_task_index == 15) {
        return task_generate_key;
    } else {
        return task_generate_key;
    }
}

TASK(task_calc_indexes) {
    uint32_t __cry = hash_to_fingerprint(__GET(_v_key));
    __SET(_v_fingerprint, __cry);
    return task_calc_indexes_index_1;
}

TASK(task_calc_indexes_index_1) {
    uint32_t __cry = hash_to_index(__GET(_v_key));
    __SET(_v_index1, __cry);
    return task_calc_indexes_index_2;
}

TASK(task_calc_indexes_index_2) {
    uint32_t fp_hash = hash_to_index(__GET(_v_fingerprint));
    uint32_t __cry = __GET(_v_index1) ^ fp_hash;
    uint32_t next_task_index;
    uint32_t next_task = __GET(_v_next_task);
    __SET(_v_index2, __cry);
    if(next_task >= task_index_t_calc_indexes_index_2) {
        next_task_index = next_task - task_index_t_calc_indexes_index_2;
    } else {
        next_task_index = TASK_NUM - next_task + task_index_t_calc_indexes_index_2;
    }
    if(next_task_index == 0) {
        return task_calc_indexes_index_2;
    } else if (next_task_index == 1) {
        return task_insert;
    } else if (next_task_index == 2) {
        return task_add;
    } else if (next_task_index == 3) {
        return task_relocate;
    } else if (next_task_index == 4) {
        return task_insert_done;
    } else if (next_task_index == 5) {
        return task_lookup;
    } else if (next_task_index == 6) {
        return task_lookup_search;
    } else if (next_task_index == 7) {
        return task_lookup_done;
    } else if (next_task_index == 8) {
        return task_print_stats;
    } else if (next_task_index == 9) {
        return task_done;
    } else if (next_task_index == 10) {
        return task_init;
    } else if (next_task_index == 11) {
        return task_init_array;
    } else if (next_task_index == 12) {
        return task_generate_key;
    } else if (next_task_index == 13) {
        return task_calc_indexes;
    } else if (next_task_index == 14) {
        return task_calc_indexes_index_1;
    } else if (next_task_index == 15) {
        return task_calc_indexes_index_2;
    } else {
        return task_calc_indexes_index_2;
    }
}

TASK(task_insert) {
    __SET(_v_next_task, task_index_t_add);
    return task_calc_indexes;
}

TASK(task_add) {
    uint32_t __cry;
    uint32_t __cry_idx = __GET(_v_index1);
    uint32_t __cry_idx2 = __GET(_v_index2);
    uint32_t fingerprint = __GET(_v_fingerprint);
    uint32_t fp_victim;
    uint32_t index_victim;
    uint32_t random_num;
    if(__GET(_v_filter[ __cry_idx ]) != 0) {
        __SET(_v_success, true);
        __cry = fingerprint;
        __SET(_v_filter[ __cry_idx ], __cry);
        return task_insert_done;
    } else {
        if(__GET(_v_filter[ __cry_idx2 ]) != 0) {
            __SET(_v_success, true);
            __cry = fingerprint;
            __SET(_v_filter[ __cry_idx2 ], __cry);
            return task_insert_done;
        } else {
            random_num = rand();
            if(random_num % 2 != 0) {
                index_victim = __cry_idx;
                fp_victim = __GET(_v_filter[ __cry_idx ]);
            } else {
                index_victim = __cry_idx2;
                fp_victim = __GET(_v_filter[ __cry_idx2 ]);
            }
            __cry = fingerprint;
            __SET(_v_filter[ index_victim ], __cry);
            __SET(_v_index1, index_victim);
            __SET(_v_fingerprint, fp_victim);
            __SET(_v_relocation_count, 0);
            return task_relocate;
        }
    }
}

TASK(task_relocate) {
    uint32_t __cry;
    uint32_t fp_victim = __GET(_v_fingerprint);
    uint32_t fp_hash_victim = fp_victim;
    uint32_t index2_victim = __GET(_v_index1) ^ fp_hash_victim;
    if(__GET(_v_filter[ index2_victim ]) != 0) {
        __SET(_v_success, true);
        __SET(_v_filter[ index2_victim ], fp_victim);
        return task_insert_done;
    } else {
        if(__GET(_v_relocation_count) >= MAX_RELOCATIONS) {
            __SET(_v_success, false);
            return task_insert_done;
        } 
        __SET(_v_relocation_count, __GET(_v_relocation_count) + 1);
        __SET(_v_index1, index2_victim);
        __cry = __GET(_v_filter[ index2_victim ]);
        __SET(_v_fingerprint, __cry);
        __SET(_v_filter[ index2_victim ], fp_victim);
        return task_relocate;
    }
}

TASK(task_insert_done) {
    uint32_t __cry = __GET(_v_inserted_count);
    __SET(_v_insert_count, __GET(_v_insert_count) + 1);
    if(__GET(_v_success) == true) {
        __cry = __cry + 1;
    } 
    __SET(_v_inserted_count, __cry);
    if(__GET(_v_insert_count) < NUM_INSERTS) {
        __SET(_v_next_task, task_index_t_insert);
        return task_generate_key;
    } else {
        __SET(_v_next_task, task_index_t_lookup);
        __SET(_v_key, init_key);
        return task_generate_key;
    }
}

TASK(task_lookup) {
    __SET(_v_next_task, task_index_t_lookup_search);
    return task_calc_indexes;
}

TASK(task_lookup_search) {
    if(__GET(_v_filter[ __GET(_v_index1) ]) == __GET(_v_fingerprint)) {
        __SET(_v_member, true);
    } else {
        if(__GET(_v_filter[ __GET(_v_index2) ]) == __GET(_v_fingerprint)) {
            __SET(_v_member, true);
        } else {
            __SET(_v_member, false);
        }
    }
    return task_lookup_done;
}

TASK(task_lookup_done) {
    uint32_t __cry = __GET(_v_member_count);
    __SET(_v_lookup_count, __GET(_v_lookup_count) + __GET(_v_lookup_count));
    if(__GET(_v_member) == true) {
        __cry = __cry + 1;
    } 
    __SET(_v_member_count, __cry);
    if(__GET(_v_lookup_count) < NUM_LOOKUPS) {
        __SET(_v_next_task, task_index_t_lookup);
        return task_generate_key;
    } else {
        return task_print_stats;
    }
}

TASK(task_print_stats) {
    return task_done;
}

TASK(task_done) {
    return NULL;
}

