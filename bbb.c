int main() {
   int res;
    const int c1 = 10 * 5 / 2;
    const int c2 = c1 / 2, c3 = c1 * 2;
    if (c1 > 24) {
        int c1 = 24;
       res=c2 - c1 * c3;
        
    }
    {
        int c2 = c1 / 4;
        res=c3 / c2;
        {
            int c3 = c1 * 4;
            res=c3 / c2;
        }
    }
   
    res=c3 / c2;
    return res;
}
