declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
declare void @memset(i32*, i32, i32)
@field = dso_local global [2 x i32][i32 0,i32 0]
define dso_local i32 @main(){
%x1=alloca [1 x i32]
%x2 = getelementptr [1 x i32], [1 x i32]* %x1, i32 0, i32 0
call void @memset(i32* %x2, i32 0, i32 4)
%x3=alloca [3 x i32]
%x4 = getelementptr [3 x i32], [3 x i32]* %x3, i32 0, i32 0
call void @memset(i32* %x4, i32 0, i32 12)
%x5=alloca i32
%x6 = getelementptr [2 x i32], [2 x i32]* @field, i32 0, i32 0
%x7=getelementptr i32,i32* %x6, i32 0
%x8= call i32 @getint()
store i32 %x8,i32* %x7
%x9 = getelementptr [2 x i32], [2 x i32]* @field, i32 0, i32 0
%x10=getelementptr i32,i32* %x9, i32 1
%x11= call i32 @getint()
store i32 %x11,i32* %x10
%x12 = add i32 0,0
%x13 = getelementptr [3 x i32], [3 x i32]* %x3, i32 0, i32 0
%x14=getelementptr i32,i32* %x13, i32 %x12
%x15 = sub i32 0,1
store i32 %x15,i32* %x14
%x16 = getelementptr [3 x i32], [3 x i32]* %x3, i32 0, i32 0
%x17=getelementptr i32,i32* %x16, i32 1
%x18 = getelementptr [3 x i32], [3 x i32]* %x3, i32 0, i32 0
%x19=getelementptr i32,i32* %x18, i32 0
%x20=load i32,i32* %x19
%x21 = sub i32 %x20,2
store i32 %x21,i32* %x17
%x22 = getelementptr [3 x i32], [3 x i32]* %x3, i32 0, i32 0
%x23=getelementptr i32,i32* %x22, i32 1
%x24=load i32,i32* %x23
store i32 %x24,i32* %x5
%x25 = getelementptr [3 x i32], [3 x i32]* %x3, i32 0, i32 0
%x26=getelementptr i32,i32* %x25, i32 2
store i32 16,i32* %x26
%x27 = getelementptr [2 x i32], [2 x i32]* @field, i32 0, i32 0
%x28=getelementptr i32,i32* %x27, i32 0
%x29=load i32,i32* %x28
%x30 = sub i32 3,%x29
%x31 = getelementptr [3 x i32], [3 x i32]* %x3, i32 0, i32 0
%x32=getelementptr i32,i32* %x31, i32 %x30
%x33=load i32,i32* %x32
%x34 = load i32, i32* %x5
%x35 = add i32 %x33,2
%x36 = add i32 %x35,%x34
call void @putint(i32 %x36)
ret i32 0
}
