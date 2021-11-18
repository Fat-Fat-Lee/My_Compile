declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
@a= dso_local global i32 6
%1 = load i32, i32* @a
%2 = add i32 %1,1
@b= dso_local global i32 %2
define dso_local i32 @main(){
%3=alloca i32
%4 = load i32, i32* @b
store i32 %4,i32* %3
%5=alloca i32
store i32 8,i32* %5
%6 = load i32, i32* %5
%7 = load i32, i32* %3
%8 = add i32 %6,%7
call void @putint(i32 %8)
ret i32 0
}
