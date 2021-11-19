int main() {
    const int ch = 48;
    int ch_;
    int i = 1;
    if (i < 12) {
        int j = 0;
        if (j < 2 * i - 1) {
            if (j % 3 == 1) {
                ch_=(ch + 1);
            } else {
                ch_=(ch);
            }
            j = j + 1;
        }
        ch_=(10);
        i = i + 1;
    }
    return 0;
}
