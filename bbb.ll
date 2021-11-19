declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
define dso_local i32 @main(){
%x1=alloca i32
store i32 3389,i32* %x1
%x2 = load i32, i32* %x1
%x3 = icmp slt i32 %x2,10000
%x4 = zext i1 %x3 to i32
%x5= icmp ne i32 %x4, 0
br i1 %x5, label %x6, label %x7
x6:
%x8 = load i32, i32* %x1
%x9 = add i32 %x8,1
store i32 %x9,i32* %x1
%x10=alloca i32
store i32 112,i32* %x10
%x11 = load i32, i32* %x10
%x12 = icmp sgt i32 %x11,10
%x13 = zext i1 %x12 to i32
%x14= icmp ne i32 %x13, 0
br i1 %x14, label %x15, label %x16
x15:
%x17 = load i32, i32* %x10
%x18 = sub i32 %x17,88
store i32 %x18,i32* %x10
%x19 = load i32, i32* %x10
%x20 = icmp slt i32 %x19,1000
%x21 = zext i1 %x20 to i32
%x22= icmp ne i32 %x21, 0
br i1 %x22, label %x23, label %x24
x23:
%x25=alloca i32
store i32 9,i32* %x25
%x26=alloca i32
store i32 11,i32* %x26
store i32 10,i32* %x25
%x27 = load i32, i32* %x10
%x28 = load i32, i32* %x25
%x29 = sub i32 %x27,%x28
store i32 %x29,i32* %x10
%x30=alloca i32
store i32 11,i32* %x30
%x31 = load i32, i32* %x10
%x32 = load i32, i32* %x30
%x33 = load i32, i32* %x26
%x34 = add i32 %x31,%x32
%x35 = add i32 %x34,%x33
store i32 %x35,i32* %x10
br label %x24
x24:
br label %x16
x16:
%x36 = load i32, i32* %x10
call void @putint(i32 %x36)
br label %x7
x7:
ret i32 0
}
