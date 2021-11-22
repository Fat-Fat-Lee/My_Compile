declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
define dso_local i32 @main(){
%x1=alloca i32
%x2=alloca i32
%x3=alloca i32
%x4=alloca i32
%x5=alloca i32
%x6= call i32 @getint()
store i32 %x6,i32* %x5
br label %x7
x7:
%x8 = load i32, i32* %x5
%x9 = icmp sgt i32 %x8,0
%x10 = zext i1 %x9 to i32
%x11= icmp ne i32 %x10, 0
br i1 %x11, label %x12, label %x13
x12:
%x14= call i32 @getint()
store i32 %x14,i32* %x2
%x15= call i32 @getint()
store i32 %x15,i32* %x3
%x16 = load i32, i32* %x5
%x17 = sub i32 %x16,1
store i32 %x17,i32* %x5
br label %x18
x18:
%x19= icmp ne i32 1, 0
br i1 %x19, label %x20, label %x21
x20:
%x22 = load i32, i32* %x2
%x23 = load i32, i32* %x3
%x24 = add i32 %x22,%x23
%x25 = add i32 %x24,1
%x26 = sdiv i32 %x25,2
store i32 %x26,i32* %x4
%x27= call i32 @getint()
store i32 %x27,i32* %x1
%x28 = load i32, i32* %x1
%x29 = icmp eq i32 %x28,0
%x30 = zext i1 %x29 to i32
%x31= icmp ne i32 %x30, 0
br i1 %x31, label %x32, label %x33
x32:
br label %x21
x33:
%x35 = load i32, i32* %x1
%x36 = icmp eq i32 %x35,1
%x37 = zext i1 %x36 to i32
%x38= icmp ne i32 %x37, 0
br i1 %x38, label %x39, label %x40
x39:
%x41 = load i32, i32* %x4
store i32 %x41,i32* %x3
br label %x42
x40:
%x43 = load i32, i32* %x1
%x44 = icmp eq i32 %x43,2
%x45 = zext i1 %x44 to i32
%x46= icmp ne i32 %x45, 0
br i1 %x46, label %x47, label %x48
x47:
%x49 = load i32, i32* %x4
store i32 %x49,i32* %x2
br label %x50
x48:
call void @putch(i32 69)
call void @putch(i32 10)
br label %x18
x50:
br label %x42
x42:
br label %x34
x34:
%x51 = load i32, i32* %x2
%x52 = load i32, i32* %x3
%x53 = icmp eq i32 %x51,%x52
%x54 = zext i1 %x53 to i32
%x55= icmp ne i32 %x54, 0
br i1 %x55, label %x56, label %x57
x56:
call void @putch(i32 67)
call void @putch(i32 10)
br label %x21
x57:
%x58 = load i32, i32* %x4
call void @putint(i32 %x58)
call void @putch(i32 10)
br label %x18
x21:
%x59 = load i32, i32* %x4
call void @putint(i32 %x59)
call void @putch(i32 10)
call void @putch(i32 10)
br label %x7
x13:
ret i32 0
}
