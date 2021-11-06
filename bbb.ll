declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
define dso_local i32 @main(){
%1=alloca i32
%2=alloca i32
store i32 56,i32* %1
store i32 4,i32* %2
%3 = load i32, i32* %1
%4 = load i32, i32* %2
%5 = add i32 %3,0
%6 = add i32 %5,4
%7 = add i32 %6,0
%8 = add i32 %7,%4
store i32 %8,i32* %1
br label %9
9:
%10 = load i32, i32* %1
%11 = add i32 0,0
%12 = add i32 %11,0
%13 = add i32 %12,0
%14 = icmp eq i32 %10,0
%15 = zext i1 %14 to i32
%16 = sub i32 %13,%15
%17= icmp ne i32 %16, 0
br i1 %17,label %18, label %22
18:
%19 = add i32 0,0
%20 = add i32 %19,0
%21 = sub i32 %20,1
store i32 %21,i32* %1
br label %26
22:
%23 = load i32, i32* %2
%24 = add i32 0,0
%25 = add i32 %24,%23
store i32 %25,i32* %1
br label %26
26:
%27 = load i32, i32* %1
ret i32 %27
}
