declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
define dso_local i32 @main(){
%1=alloca i32
store i32 5,i32* %1
%2=alloca i32
store i32 10,i32* %2
br label %3
3:
%4 = load i32, i32* %1
%5 = icmp eq i32 %4,5
%6 = zext i1 %5 to i32
%7= icmp ne i32 %6, 0
br i1 %7,label %8, label %9

8:
br label %9
9:
%10 = load i32, i32* %2
%11 = icmp eq i32 %10,10
%12 = zext i1 %11 to i32
%13= icmp ne i32 %12, 0
br i1 %13,label %14, label %15

14:
store i32 25,i32* %1
br label %15

15:

br label %19
16:
%17 = load i32, i32* %1
%18 = add i32 %17,15
store i32 %18,i32* %1
br label %19

19:
%20 = load i32, i32* %1
ret i32 %20
}
