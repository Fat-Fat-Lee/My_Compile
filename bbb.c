#include<stdio.h>
const int TAPE_LEN = 65536, BUFFER_LEN = 32768;
int       ptr = 0;

int main() {
    int i = 0, len = 33;
   int tape[500];
    int program[300]={10,43,43,43,43,43,43,43,43,91,45,62,43,43,43,43,43,43,43,43,60,93,62,43,43,43,43,43,43,43,43,43,46};
    int cur_char, loop;
    i = 0;

    while (program[i]) {
        cur_char = program[i];
		putint(tape[ptr]);
        if (cur_char == 62) {
            // '>'
            ptr = ptr + 1;
//            putint(cur_char);
//            putch(47);
//            putint(ptr);
//            putch(44);
        } else if (cur_char == 60) {
            // '<'
            ptr = ptr - 1;
//            putint(cur_char);
//            putch(47);
//            putint(ptr);
//            putch(44);
        } else if (cur_char == 43) {
            // '+'
            tape[ptr] = tape[ptr] + 1;
//             putint(cur_char);
//            putch(47);
//            putint( tape[ptr]);
//            putch(10);
        } else if (cur_char == 45) {
            // '-'
            tape[ptr] = tape[ptr] - 1;
// 			putint(cur_char);
//            putch(47);
//            putint( tape[ptr]);
//            putch(10);
        } else if (cur_char == 46) {
            // '.'
            putch(64);
            putch(tape[ptr]);
        
            
        } else if (cur_char == 44) {
            // ','
            tape[ptr] = getch();
        } else if (cur_char == 93 && tape[ptr]) {
            // ']'
            loop = 1;
            while (loop > 0) {
                i        = i - 1;
                cur_char = program[i];
	
                if (cur_char == 91) {
                    // '['
                    loop = loop - 1;
                } else if (cur_char == 93) {
                    // ']'
                    loop = loop + 1;
                }
            }
        }
        i = i + 1;
    }
    return 0;
}
