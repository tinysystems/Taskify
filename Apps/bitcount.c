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
uint32_t bits[ 256 ] = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 4, 5, 5, 6, 5, 6, 6, 7, 5, 6, 6, 7, 6, 7, 7, 8};
uint32_t SEED = 4;
uint32_t ITER = 10;
uint32_t CHAR_BIT = 8;
uint32_t plus = 13;


// Define task-shared persistent variables.
__shared(
    uint32_t pinCont;
    uint32_t n_0;
    uint32_t n_1;
    uint32_t n_2;
    uint32_t n_3;
    uint32_t n_4;
    uint32_t n_5;
    uint32_t n_6;
    uint32_t function;
    uint32_t iteration;
    uint32_t seed;
)


// Declare tasks that will be implemented
ENTRY_TASK(t_init);
TASK(t_select_func);
TASK(t_bit_count);
TASK(t_bitcount);
TASK(t_ntbl_bitcnt);
TASK(t_ntbl_bitcount);
TASK(t_BW_btbl_bitcount);
TASK(t_AR_btbl_bitcount);
TASK(t_bit_shifter);
TASK(t_end);


// Called at the very first boot
void thread1_init(){
    // create a thread with priority 15 and entry task t_init
    __CREATE(15, t_init);
    __SIGNAL(15);
}


// Implementation of all tasks that are declared above
ENTRY_TASK(t_init) {
    uint32_t _seed = rand();
    __SET(seed, _seed);
    __SET(seed, 45697649);
    __SET(pinCont, 1);
    __SET(function, 0);
    __SET(n_0, 0);
    __SET(n_1, 0);
    __SET(n_2, 0);
    __SET(n_3, 0);
    __SET(n_4, 0);
    __SET(n_5, 0);
    __SET(n_6, 0);
    return t_select_func;
}

TASK(t_select_func) {
    uint32_t _function = __GET(function);
    __SET(iteration, 0);
    if(_function == 0) {
        __SET(function, _function + 1);
        return t_bit_count;
    } else if (_function == 1) {
        __SET(function, _function + 1);
        return t_bitcount;
    } else if (_function == 2) {
        __SET(function, _function + 1);
        return t_ntbl_bitcnt;
    } else if (_function == 3) {
        __SET(function, _function + 1);
        return t_ntbl_bitcount;
    } else if (_function == 4) {
        __SET(function, _function + 1);
        return t_BW_btbl_bitcount;
    } else if (_function == 5) {
        __SET(function, _function + 1);
        return t_AR_btbl_bitcount;
    } else if (_function == 6) {
        __SET(function, _function + 1);
        return t_bit_shifter;
    } else {
        __SET(function, _function + 1);
        return t_end;
    }
}

TASK(t_bit_count) {
    uint32_t _seed = __GET(seed);
    uint32_t _n_0 = __GET(n_0);
    uint32_t _iteration = __GET(iteration);
    uint32_t tmp_seed = _seed;
    uint32_t temp = 0;
    _seed = tmp_seed + plus;
    if(tmp_seed > 0) {
        temp = temp + 1;
        tmp_seed = tmp_seed & (tmp_seed - 1);
        
        while(tmp_seed != 0) {
            temp = temp + 1;
            tmp_seed = tmp_seed & (tmp_seed - 1);
        }
    } 
    _iteration = _iteration + 1;
    __SET(seed, _seed);
    __SET(n_0, _n_0 + temp);
    __SET(iteration, _iteration);
    if(_iteration < ITER) {
        return t_bit_count;
    } else {
        return t_select_func;
    }
}

TASK(t_bitcount) {
    uint32_t _seed = __GET(seed);
    uint32_t _n_1 = __GET(n_1);
    uint32_t _iteration = __GET(iteration);
    uint32_t tmp_seed = _seed;
    _seed = tmp_seed + plus;
    tmp_seed = ((tmp_seed & ) >> 1) + (tmp_seed & );
    tmp_seed = ((tmp_seed & ) >> 2) + (tmp_seed & );
    tmp_seed = ((tmp_seed & ) >> 4) + (tmp_seed & );
    tmp_seed = ((tmp_seed & ) >> 8) + (tmp_seed & );
    tmp_seed = ((tmp_seed & ) >> 16) + (tmp_seed & );
    _iteration = _iteration + 1;
    __SET(seed, _seed);
    __SET(n_1, _n_1 + tmp_seed);
    __SET(iteration, _iteration);
    if(_iteration < ITER) {
        return t_bitcount;
    } else {
        return t_select_func;
    }
}

TASK(t_ntbl_bitcnt) {
    uint32_t _seed = __GET(seed);
    uint32_t _n_2 = __GET(n_2);
    uint32_t _iteration = __GET(iteration);
    uint32_t tmp_seed = _seed;
    uint32_t function_result = non_recursive_cnt(tmp_seed);
    _seed = tmp_seed + plus;
    _iteration = _iteration + 1;
    __SET(seed, _seed);
    __SET(n_2, _n_2 + function_result);
    __SET(iteration, _iteration);
    if(_iteration < ITER) {
        return t_ntbl_bitcnt;
    } else {
        return t_select_func;
    }
}

TASK(t_ntbl_bitcount) {
    uint32_t _seed = __GET(seed);
    uint32_t _n_3 = __GET(n_3);
    uint32_t _iteration = __GET(iteration);
    uint32_t tmp_seed = _seed;
    uint32_t __cry = _seed;
    uint32_t index = __cry & ;
    _n_3 = _n_3 + bits[ index ];
    index = (__cry & ) >> 4;
    _n_3 = _n_3 + bits[ index ];
    index = (__cry & ) >> 8;
    _n_3 = _n_3 + bits[ index ];
    index = (__cry & ) >> 12;
    _n_3 = _n_3 + bits[ index ];
    index = (__cry & ) >> 16;
    _n_3 = _n_3 + bits[ index ];
    index = (__cry & ) >> 20;
    _n_3 = _n_3 + bits[ index ];
    index = (__cry & ) >> 24;
    _n_3 = _n_3 + bits[ index ];
    index = (__cry & ) >> 28;
    _n_3 = _n_3 + bits[ index ];
    _seed = tmp_seed + plus;
    _iteration = _iteration + 1;
    __SET(n_3, _n_3);
    __SET(seed, _seed);
    __SET(iteration, _iteration);
    if(_iteration < ITER) {
        return t_ntbl_bitcount;
    } else {
        return t_select_func;
    }
}

TASK(t_BW_btbl_bitcount) {
    uint32_t _seed = __GET(seed);
    uint32_t _n_4 = __GET(n_4);
    uint32_t _iteration = __GET(iteration);
    uint32_t index = _seed << 24 >> 24;
    _n_4 = _n_4 + bits[ index ];
    index = _seed << 16 >> 24;
    _n_4 = _n_4 + bits[ index ];
    index = _seed << 8 >> 24;
    _n_4 = _n_4 + bits[ index ];
    index = _seed >> 24;
    _n_4 = _n_4 + bits[ index ];
    _seed = _seed + plus;
    _iteration = _iteration + 1;
    __SET(n_4, _n_4);
    __SET(seed, _seed);
    __SET(iteration, _iteration);
    if(_iteration < ITER) {
        return t_BW_btbl_bitcount;
    } else {
        return t_select_func;
    }
}

TASK(t_AR_btbl_bitcount) {
    uint32_t _seed = __GET(seed);
    uint32_t _n_5 = __GET(n_5);
    uint32_t _iteration = __GET(iteration);
    uint32_t index = _seed << 24 >> 24;
    _n_5 = _n_5 + bits[ index ];
    index = _seed << 16 >> 24;
    _n_5 = _n_5 + bits[ index ];
    index = _seed << 8 >> 24;
    _n_5 = _n_5 + bits[ index ];
    index = _seed >> 24;
    _n_5 = _n_5 + bits[ index ];
    _seed = _seed + plus;
    _iteration = _iteration + 1;
    __SET(n_5, _n_5);
    __SET(seed, _seed);
    __SET(iteration, _iteration);
    if(_iteration < ITER) {
        return t_AR_btbl_bitcount;
    } else {
        return t_select_func;
    }
}

TASK(t_bit_shifter) {
    uint32_t _seed = __GET(seed);
    uint32_t _n_6 = __GET(n_6);
    uint32_t _iteration = __GET(iteration);
    uint32_t i = 0;
    uint32_t nn;
    uint32_t tmp_seed = _seed;
    
    for (nn = 0; 0 < tmp_seed; tmp_seed = tmp_seed >> 1) {
        i = i + 1;
        if(i < 8 * CHAR_BIT) {
            nn = nn + (tmp_seed & 1);
        } else {
            break;
        }
    }
    _n_6 = _n_6 + nn;
    _seed = _seed + plus;
    _iteration = _iteration + 1;
    __SET(n_6, _n_6);
    __SET(seed, _seed);
    __SET(iteration, _iteration);
    if(_iteration < ITER) {
        return t_bit_shifter;
    } else {
        return t_select_func;
    }
}

TASK(t_end) {
    uint32_t _pinCont = __GET(pinCont);
    __SET(pinCont, 0);
    return NULL;
}

