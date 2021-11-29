declare i32 @getint()
declare i32 @getch()
declare i32 @getarray(i32*)
declare void @putint(i32)
declare void @putch(i32)
declare void @putarray(i32, i32*)
declare void @memset(i32*, i32, i32)
define dso_local i32 @relu_reg( i32 %x1 ){
%x2 = alloca i32 
store i32 %x1, i32* %x2
%x3 = load i32, i32* %x2
ret i32 %x3
ret i32 0
}
define dso_local i32 @model( [ 5 x i32 ]* %x4 ){
%x5 = alloca [ 5 x i32 ]* 
store [ 5 x i32 ]* %x4,[ 5 x i32 ]* * %x5
%x6 = load [5 x i32]*, [5 x i32]* * %x5
%x7 = getelementptr [5 x i32], [5 x i32]* %x6, i32 0 
%x8 = mul i32 0, 5
%x9 = add i32 %x8, 0
%x10 = getelementptr [5 x i32], [5 x i32]* %x7, i32 0, i32 %x9
%x11=load i32,i32* %x10
%x12 = add i32 0,%x11
%x13= call i32 @relu_reg(i32 %x12)
%x14 = add i32 0,%x13
%x15 = icmp sgt i32 %x14,0
%x16 = zext i1 %x15 to i32
%x17= icmp ne i32 %x16, 0
br i1 %x17, label %x18, label %x19
x18:
ret i32 1

x19:
ret i32 0
ret i32 0
}
define dso_local i32 @main(){
%x20=alloca [5 x [5 x i32]]
%x21 = getelementptr [5 x [5 x i32]],[5 x [5 x i32]]* %x20, i32 0, i32 0
%x22 = getelementptr [5 x i32], [5 x i32]* %x21, i32 0, i32 0
call void @memset(i32* %x22, i32 0, i32 100)
%x23 = getelementptr [5 x [5 x i32]],[5 x [5 x i32]]* %x20, i32 0, i32 0
%x24= call i32 @model( [ 5 x i32 ]* %x23)
%x25= icmp ne i32 %x24, 0
br i1 %x25, label %x26, label %x27
x26:
br label %x27
x27:
ret i32 0
ret i32 0
}
