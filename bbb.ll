declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
%1=alloca i32
store i32 6,i32* %1
%2=alloca i32
%3 = load i32, i32* %1
%4 = add i32 %3,1
store i32 %4,i32* %2
define dso_local i32 @main(){
%5=alloca i32
%6 = load i32, i32* %2
store i32 %6,i32* %5
%7=alloca i32
store i32 8,i32* %7
%8 = load i32, i32* %7
%9 = load i32, i32* %5
%10 = add i32 %8,%9
ret %10
}
