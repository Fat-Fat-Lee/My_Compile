declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
define dso_local i32 @main(){
%x1=alloca i32
store i32 48,i32* %x1
%x2=alloca i32
store i32 1,i32* %x2
br label %x3
x3:
%x4 = load i32, i32* %x2
%x5 = icmp slt i32 %x4,12
%x6 = zext i1 %x5 to i32
%x7= icmp ne i32 %x6, 0
br i1 %x7, label %x8, label %x9
x8:
%x10=alloca i32
store i32 0,i32* %x10
br label %x11
x11:
%x12 = load i32, i32* %x10
%x13 = load i32, i32* %x2
%x14 = mul i32 2,%x13
%x15 = sub i32 %x14,1
%x16 = icmp slt i32 %x12,%x15
%x17 = zext i1 %x16 to i32
%x18= icmp ne i32 %x17, 0
br i1 %x18, label %x19, label %x20
x19:
%x21 = load i32, i32* %x10
%x22 = srem i32 %x21,3
%x23 = icmp eq i32 %x22,1
%x24 = zext i1 %x23 to i32
%x25= icmp ne i32 %x24, 0
br i1 %x25, label %x26, label %x27
x26:
%x28 = load i32, i32* %x1
%x29 = add i32 %x28,1
call void @putch(i32 %x29)
br label %x30
x27:
%x31 = load i32, i32* %x1
call void @putch(i32 %x31)
br label %x30
x30:
%x32 = load i32, i32* %x10
%x33 = add i32 %x32,1
store i32 %x33,i32* %x10
br label %x11
x20:
call void @putch(i32 10)
%x34 = load i32, i32* %x2
%x35 = add i32 %x34,1
store i32 %x35,i32* %x2
br label %x3
x9:
ret i32 0
}
