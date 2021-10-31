declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
define dso_local i32 @main(){
%1=alloca i32
store i32 8,i32* %1
%2=alloca i32
store i32 456,i32* %2
%3=alloca i32
store i32 8456,i32* %3
%4=alloca i32
%5 = load i32, i32* %3
%6 = load i32, i32* %2
%7 = load i32, i32* %1
%8 = sub i32 %5,%6
%9 = sdiv i32 %8,1000
%10 = sub i32 %9,%7
store i32 %10,i32* %4
%11=alloca i32
store i32 2,i32* %11
%12 = load i32, i32* %4
%13 = load i32, i32* %11
%14 = add i32 %12,%13
store i32 %14,i32* %4
%15 = load i32, i32* %4
%16 = load i32, i32* %11
%17 = sub i32 %15,%16
%18 = add i32 %17,0
ret i32 %18
}
