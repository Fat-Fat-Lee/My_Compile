declare i32 @getint()
declare i32 @getch()
declare i32 @getarray(i32*)
declare void @putint(i32)
declare void @putch(i32)
declare void @putarray(i32, i32*)
declare void @memset(i32*, i32, i32)
define dso_local i32 @main(){
%x1=alloca i32
%x2 = sub i32 0,106
%x3 = mul i32 1,%x2
%x4 = add i32 0,%x3
%x5 = sub i32 0,125
%x6 = mul i32 4,%x5
%x7 = add i32 %x4,%x6
store i32 %x7,i32* %x1
%x8 = load i32, i32* %x1
call void @putint(i32 %x8)
ret i32 0
ret i32 0
}
