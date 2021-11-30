declare i32 @getint()
declare i32 @getch()
declare i32 @getarray(i32*)
declare void @putint(i32)
declare void @putch(i32)
declare void @putarray(i32, i32*)
declare void @memset(i32*, i32, i32)
@a= dso_local global i32 -1
@b= dso_local global i32 1
define dso_local i32 @inc_a(){
%x1=alloca i32
%x2 = load i32, i32* @a
store i32 %x2,i32* %x1
%x3 = load i32, i32* %x1
%x4 = add i32 %x3,1
store i32 %x4,i32* %x1
%x5 = load i32, i32* %x1
store i32 %x5,i32* @a
%x6 = load i32, i32* @a
ret i32 %x6
ret i32 0
}
define dso_local i32 @main(){
%x7=alloca i32
store i32 5,i32* %x7
br label %x8
x8:
%x12 = load i32, i32* %x7
%x13 = icmp sge i32 %x12,0
%x14 = zext i1 %x13 to i32
%x15= icmp ne i32 %x14, 0
br i1 %x15, label %x9, label %x11
x9:
%x19= call i32 @inc_a()
%x20= icmp ne i32 %x19, 0
br i1 %x20, label %x21, label %x18
x21:
%x22= call i32 @inc_a()
%x23= icmp ne i32 %x22, 0
br i1 %x23, label %x24, label %x18
x24:
%x25= call i32 @inc_a()
%x26= icmp ne i32 %x25, 0
br i1 %x26, label %x16, label %x18
x16:
%x27 = load i32, i32* @a
call void @putint(i32 %x27)
call void @putch(i32 32)
%x28 = load i32, i32* @b
call void @putint(i32 %x28)
call void @putch(i32 10)
br label %x18
x18:
%x32= call i32 @inc_a()
%x33 = icmp slt i32 %x32,14
%x34 = zext i1 %x33 to i32
%x35= icmp ne i32 %x34, 0
br i1 %x35, label %x29, label %x31
x31:
%x37= call i32 @inc_a()
%x38= icmp ne i32 %x37, 0
br i1 %x38, label %x39, label %x36
x39:
%x40= call i32 @inc_a()
%x41= call i32 @inc_a()
%x42 = sub i32 %x40,%x41
%x43 = add i32 %x42,1
%x44= icmp ne i32 %x43, 0
br i1 %x44, label %x29, label %x36
x29:
%x45 = load i32, i32* @a
call void @putint(i32 %x45)
call void @putch(i32 10)
%x46 = load i32, i32* @b
%x47 = mul i32 %x46,2
store i32 %x47,i32* @b
br label %x48
x36:
%x49= call i32 @inc_a()
br label %x48
x48:
%x50 = load i32, i32* %x7
%x51 = sub i32 %x50,1
store i32 %x51,i32* %x7
br label %x8
x11:
%x52 = load i32, i32* @a
call void @putint(i32 %x52)
call void @putch(i32 32)
%x53 = load i32, i32* @b
call void @putint(i32 %x53)
call void @putch(i32 10)
ret i32 0
ret i32 0
}
