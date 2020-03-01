#include <stdio.h>
#define CHAR_BIT 8
#define ITER 10

static char bits[256] = {
        0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4,  /* 0   - 15  */
        1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,  /* 16  - 31  */
        1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,  /* 32  - 47  */
        2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,  /* 48  - 63  */
        1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,  /* 64  - 79  */
        2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,  /* 80  - 95  */
        2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,  /* 96  - 111 */
        3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,  /* 112 - 127 */
        1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,  /* 128 - 143 */
        2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,  /* 144 - 159 */
        2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,  /* 160 - 175 */
        3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,  /* 176 - 191 */
        2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,  /* 192 - 207 */
        3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,  /* 208 - 223 */
        3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,  /* 224 - 239 */
        4, 5, 5, 6, 5, 6, 6, 7, 5, 6, 6, 7, 6, 7, 7, 8   /* 240 - 255 */
};
static unsigned int plus = 13;
unsigned int seed = 45697649;
unsigned int iteration;
unsigned int function;
unsigned int n_0, n_1, n_2, n_3, n_4, n_5, n_6;

/********************************************/
int non_recursive_cnt(unsigned int x){
    int cnt = bits[(int)(x & 0x0000000FL)];

    while (0L != (x >>= 4)) {
        cnt += bits[(int)(x & 0x0000000FL)];
    }

    return cnt;
}
/********************************************/


void t_bit_count() {
    printf ("t_bit_count task \n");
    unsigned int tmp_seed = seed;

    seed = seed + plus;
    unsigned temp = 0;
    if(tmp_seed) do
        temp++;
    while (0 != (tmp_seed = tmp_seed&(tmp_seed-1)));

    n_0 += (int)temp;
    iteration++;

    // printf("** n_0 %d \n", n_0);
    if (iteration < ITER) {
      t_bit_count();
    }
}

void t_bitcount() {
    printf ("t_bit_count task \n");
    unsigned int tmp_seed = seed;

    seed = seed + plus;
    tmp_seed = ((tmp_seed & 0xAAAAAAAAL) >>  1) + (tmp_seed & 0x55555555L);
    tmp_seed = ((tmp_seed & 0xCCCCCCCCL) >>  2) + (tmp_seed & 0x33333333L);
    tmp_seed = ((tmp_seed & 0xF0F0F0F0L) >>  4) + (tmp_seed & 0x0F0F0F0FL);
    tmp_seed = ((tmp_seed & 0xFF00FF00L) >>  8) + (tmp_seed & 0x00FF00FFL);
    tmp_seed = ((tmp_seed & 0xFFFF0000L) >> 16) + (tmp_seed & 0x0000FFFFL);

    n_1 += (int)tmp_seed;
    iteration++;

    //printf("** n_1 %d \n", n_1);
    if (iteration < ITER) {
      t_bitcount();
    }
}




void t_ntbl_bitcnt() {
      printf ("t_ntbl_bitcnt task \n");
    n_2 += non_recursive_cnt(seed);
    seed = seed + plus;
    //printf("** n_2 %d \n", n_2);
    iteration++;

    if (iteration < ITER) {
      t_ntbl_bitcnt();
    }
}

void t_ntbl_bitcount() {
    printf("t_ntbl_bitcount task \n");
    unsigned int __cry = seed;

    n_3 += bits[ (int) (__cry & 0x0000000FUL)       ] +
        bits[ (int)((__cry & 0x000000F0UL) >> 4) ] +
        bits[ (int)((__cry & 0x00000F00UL) >> 8) ] +
        bits[ (int)((__cry & 0x0000F000UL) >> 12)] +
        bits[ (int)((__cry & 0x000F0000UL) >> 16)] +
        bits[ (int)((__cry & 0x00F00000UL) >> 20)] +
        bits[ (int)((__cry & 0x0F000000UL) >> 24)] +
        bits[ (int)((__cry & 0xF0000000UL) >> 28)];
    seed = seed + plus;
    /*
    printf("%d, ", (int) (__cry & 0x0000000FUL));
    printf("%d, ", (int)((__cry & 0x000000F0UL) >> 4));
    printf("%d, ", (int)((__cry & 0x00000F00UL) >> 8));
    printf("%d, ", (int)((__cry & 0x0000F000UL) >> 12));
    printf("%d, ", (int)((__cry & 0x000F0000UL) >> 16));
    printf("%d, ", (int)((__cry & 0x00F00000UL) >> 20));
    printf("%d, ", (int)((__cry & 0x0F000000UL) >> 24));
    printf("%d \n", (int)((__cry & 0xF0000000UL) >> 28));
    */
    //printf("** n_3 %d \n", n_3);
    iteration++;

    if (iteration < ITER) {
      t_ntbl_bitcount();
    }
}


void t_BW_btbl_bitcount() {
    printf("t_BW_btbl_bitcount task \n");

    union {
        unsigned char ch[6];
        long y;
    } U;

    U.y = seed;

    int aa = U.ch[0] + U.ch[1] + U.ch[2] + U.ch[3]; 
    n_4 += bits[ U.ch[0] ] + bits[ U.ch[1] ] + bits[ U.ch[2] ] + bits[ U.ch[3] ];

    seed = seed + plus;
    //printf("** n_4 %d \n", n_4);
    //printf("%d, %d, %d, %d \n", seed << 24 >> 24, seed << 16 >> 24,  seed << 8 >> 24, seed >> 24);
    //printf("%d, %d, %d, %d \n", U.ch[0], U.ch[1], U.ch[2], U.ch[3]);
    iteration++;

    if (iteration < ITER) {
      t_BW_btbl_bitcount();
    }
}

void t_AR_btbl_bitcount() {
    printf("t_AR_btbl_bitcount task \n");

    unsigned char * Ptr = (unsigned char *) &seed ;

    //printf("%d, ", *Ptr);
    n_5  += bits[ *Ptr++ ];
    //printf("%d, ", *Ptr);
    n_5 += bits[ *Ptr++ ];
    //printf("%d, ", *Ptr);
    n_5 += bits[ *Ptr++ ];
    //printf("%d \n", *Ptr);
    n_5 += bits[ *Ptr ];

    seed = seed + plus;
    //printf("** n_5 %d \n", n_5);
    iteration++;

    if (iteration < ITER) {
      t_AR_btbl_bitcount();
    }
}

void t_bit_shifter() {
    printf("t_bit_shifter task \n");

    unsigned i, nn;
    unsigned int tmp_seed = seed;
    for (i = nn = 0; tmp_seed && (i < (sizeof(long) * CHAR_BIT)); ++i, tmp_seed >>= 1)
        nn += (unsigned)(tmp_seed & 1L);
    n_6 += nn;

    seed = seed + plus;
    //printf("** n_6 %d \n", n_6);
    iteration++;

    if (iteration < ITER) {
      t_bit_shifter();
    }
}

void t_end() {
  printf("***** %d \n", n_0);
  printf("***** %d \n", n_1);
  printf("***** %d \n", n_2);
  printf("***** %d \n", n_3);
  printf("***** %d \n", n_4);
  printf("***** %d \n", n_5);
  printf("***** %d \n", n_6);
}


int main(void) {
    printf("\n\n");
    n_0 = n_1 = n_2 = n_3 = n_4 = n_5 = n_6 = 0;
    
  while(1) {
    iteration = 0;
    if (function == 0) {
      function++;
      t_bit_count();
    } else if (function == 1){
      function++;
      t_bitcount();
    } else if (function == 2){
      function++;
      t_ntbl_bitcnt();
    } else if (function == 3){
      function++;
      t_ntbl_bitcount();
    } else if (function == 4){
      function++;
      t_BW_btbl_bitcount();
    } else if (function == 5){
      function++;
      t_AR_btbl_bitcount();
    } else if (function == 6){
      function++;
      t_bit_shifter();
    } else {
      function++;
      t_end();
      break;
    }
  }

    return 0;
}



