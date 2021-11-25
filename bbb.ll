declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
declare void @memset(i32*, i32, i32)
@TAPE_LEN= dso_local global i32 6
@BUFFER_LEN= dso_local global i32 3
@tape = dso_local global [6 x i32][i32 0,i32 0,i32 0,i32 0,i32 0,i32 0]
@program = dso_local global [3 x i32][i32 0,i32 0,i32 0]
@ptr= dso_local global i32 0
define dso_local i32 @main(){
%x1=alloca i32
store i32 0,i32* %x1
%x2=alloca i32
store i32 5,i32* %x2
%x3=alloca i32
%x4=alloca i32
store i32 0,i32* %x1
br label %x5
x5:
%x6 = load i32, i32* %x1
%x7 = getelementptr [3 x i32], [3 x i32]* @program, i32 0, i32 0
%x8=getelementptr i32,i32* %x7, i32 %x6
%x9=load i32,i32* %x8
%x10= icmp ne i32 %x9, 0
br i1 %x10, label %x11, label %x12
x11:
%x13 = load i32, i32* @ptr
%x14 = getelementptr [6 x i32], [6 x i32]* @tape, i32 0, i32 0
%x15=getelementptr i32,i32* %x14, i32 %x13
%x16 = load i32, i32* @ptr
%x17 = getelementptr [6 x i32], [6 x i32]* @tape, i32 0, i32 0
%x18=getelementptr i32,i32* %x17, i32 %x16
%x19=load i32,i32* %x18
%x20 = sub i32 %x19,1
store i32 %x20,i32* %x15
br label %x5
x12:
%x21 = load i32, i32* %x3
%x22 = load i32, i32* @ptr
%x23 = getelementptr [6 x i32], [6 x i32]* @tape, i32 0, i32 0
%x24=getelementptr i32,i32* %x23, i32 %x22
%x25=load i32,i32* %x24
%x26 = icmp eq i32 %x21,93
%x27 = zext i1 %x26 to i32
%x28 = and i32 %x27,%x25
%x29= icmp ne i32 %x28, 0
br i1 %x29, label %x30, label %x31
x30:
br label %x32
x32:
%x33 = load i32, i32* %x4
%x34 = icmp sgt i32 %x33,0
%x35 = zext i1 %x34 to i32
%x36= icmp ne i32 %x35, 0
br i1 %x36, label %x37, label %x38
x37:
%x39 = load i32, i32* %x1
%x40 = getelementptr [3 x i32], [3 x i32]* @program, i32 0, i32 0
%x41=getelementptr i32,i32* %x40, i32 %x39
%x42=load i32,i32* %x41
store i32 %x42,i32* %x3
%x43 = load i32, i32* %x3
%x44 = icmp eq i32 %x43,91
%x45 = zext i1 %x44 to i32
%x46= icmp ne i32 %x45, 0
br i1 %x46, label %x47, label %x48
x47:
%x49 = load i32, i32* %x4
%x50 = sub i32 %x49,1
store i32 %x50,i32* %x4
br label %x48
x48:
br label %x32
x38:
br label %x31
x31:
ret i32 0
}
