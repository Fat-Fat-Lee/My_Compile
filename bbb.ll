declare i32 @getint()
declare i32 @getch()
declare i32 @getarray(i32*)
declare void @putint(i32)
declare void @putch(i32)
declare void @putarray(i32, i32*)
declare void @memset(i32*, i32, i32)
@ans = dso_local global [50 x i32]zeroinitializer
@sum= dso_local global i32 0
@n= dso_local global i32 0
@row = dso_local global [50 x i32]zeroinitializer
@line1 = dso_local global [50 x i32]zeroinitializer
@line2 = dso_local global [100 x i32]zeroinitializer
define dso_local void @printans(){
%x1 = load i32, i32* @sum
%x2 = add i32 %x1,1
store i32 %x2,i32* @sum
%x3=alloca i32
store i32 1,i32* %x3
br label %x4
x4:
%x5 = load i32, i32* %x3
%x6 = load i32, i32* @n
%x7 = icmp sle i32 %x5,%x6
%x8 = zext i1 %x7 to i32
%x9= icmp ne i32 %x8, 0
br i1 %x9, label %x10, label %x11
x10:
%x12 = load i32, i32* %x3
%x13 = getelementptr [50 x i32], [50 x i32]* @ans, i32 0, i32 0
%x14=getelementptr i32,i32* %x13, i32 %x12
%x15=load i32,i32* %x14
call void @putint(i32 %x15)
%x16 = load i32, i32* %x3
%x17 = load i32, i32* @n
%x18 = icmp eq i32 %x16,%x17
%x19 = zext i1 %x18 to i32
%x20= icmp ne i32 %x19, 0
br i1 %x20, label %x21, label %x22
x21:
call void @putch(i32 10)
ret void

x22:
call void @putch(i32 32)
br label %x23
x23:
%x24 = load i32, i32* %x3
%x25 = add i32 %x24,1
store i32 %x25,i32* %x3
br label %x4
x11:
ret void
}
define dso_local void @f( i32 %x26 ){
%x27 = alloca i32 
store i32 %x26, i32* %x27
%x28=alloca i32
store i32 1,i32* %x28
br label %x29
x29:
%x30 = load i32, i32* %x28
%x31 = load i32, i32* @n
%x32 = icmp sle i32 %x30,%x31
%x33 = zext i1 %x32 to i32
%x34= icmp ne i32 %x33, 0
br i1 %x34, label %x35, label %x36
x35:
%x37 = load i32, i32* %x28
%x38 = getelementptr [50 x i32], [50 x i32]* @row, i32 0, i32 0
%x39=getelementptr i32,i32* %x38, i32 %x37
%x40=load i32,i32* %x39
%x41 = load i32, i32* %x27
%x42 = load i32, i32* %x28
%x43 = add i32 %x41,%x42
%x44 = getelementptr [50 x i32], [50 x i32]* @line1, i32 0, i32 0
%x45=getelementptr i32,i32* %x44, i32 %x43
%x46=load i32,i32* %x45
%x47 = load i32, i32* @n
%x48 = load i32, i32* %x27
%x49 = load i32, i32* %x28
%x50 = add i32 %x47,%x48
%x51 = sub i32 %x50,%x49
%x52 = getelementptr [100 x i32], [100 x i32]* @line2, i32 0, i32 0
%x53=getelementptr i32,i32* %x52, i32 %x51
%x54=load i32,i32* %x53
%x55 = icmp ne i32 %x40,1
%x56 = zext i1 %x55 to i32
%x57 = icmp eq i32 %x46,0
%x58 = zext i1 %x57 to i32
%x59= icmp ne i32 %x58, 0
%x60 = zext i1 %x59 to i32
%x61= icmp ne i32 %x56, 0
%x62 = zext i1 %x61 to i32
%x63 = and i32 %x62,%x60
%x64 = icmp eq i32 %x54,0
%x65 = zext i1 %x64 to i32
%x66= icmp ne i32 %x65, 0
%x67 = zext i1 %x66 to i32
%x68= icmp ne i32 %x63, 0
%x69 = zext i1 %x68 to i32
%x70 = and i32 %x69,%x67
%x71= icmp ne i32 %x70, 0
br i1 %x71, label %x72, label %x73
x72:
%x74 = load i32, i32* %x27
%x75 = getelementptr [50 x i32], [50 x i32]* @ans, i32 0, i32 0
%x76=getelementptr i32,i32* %x75, i32 %x74
%x77 = load i32, i32* %x28
store i32 %x77,i32* %x76
%x78 = load i32, i32* %x27
%x79 = load i32, i32* @n
%x80 = icmp eq i32 %x78,%x79
%x81 = zext i1 %x80 to i32
%x82= icmp ne i32 %x81, 0
br i1 %x82, label %x83, label %x84
x83:
call void @printans()
br label %x84
x84:
%x85 = load i32, i32* %x28
%x86 = getelementptr [50 x i32], [50 x i32]* @row, i32 0, i32 0
%x87=getelementptr i32,i32* %x86, i32 %x85
store i32 1,i32* %x87
%x88 = load i32, i32* %x27
%x89 = load i32, i32* %x28
%x90 = add i32 %x88,%x89
%x91 = getelementptr [50 x i32], [50 x i32]* @line1, i32 0, i32 0
%x92=getelementptr i32,i32* %x91, i32 %x90
store i32 1,i32* %x92
%x93 = load i32, i32* @n
%x94 = load i32, i32* %x27
%x95 = load i32, i32* %x28
%x96 = add i32 %x93,%x94
%x97 = sub i32 %x96,%x95
%x98 = getelementptr [100 x i32], [100 x i32]* @line2, i32 0, i32 0
%x99=getelementptr i32,i32* %x98, i32 %x97
store i32 1,i32* %x99
%x100 = load i32, i32* %x27
%x101 = add i32 %x100,1
call void @f(i32 %x101)
%x102 = load i32, i32* %x28
%x103 = getelementptr [50 x i32], [50 x i32]* @row, i32 0, i32 0
%x104=getelementptr i32,i32* %x103, i32 %x102
store i32 0,i32* %x104
%x105 = load i32, i32* %x27
%x106 = load i32, i32* %x28
%x107 = add i32 %x105,%x106
%x108 = getelementptr [50 x i32], [50 x i32]* @line1, i32 0, i32 0
%x109=getelementptr i32,i32* %x108, i32 %x107
store i32 0,i32* %x109
%x110 = load i32, i32* @n
%x111 = load i32, i32* %x27
%x112 = load i32, i32* %x28
%x113 = add i32 %x110,%x111
%x114 = sub i32 %x113,%x112
%x115 = getelementptr [100 x i32], [100 x i32]* @line2, i32 0, i32 0
%x116=getelementptr i32,i32* %x115, i32 %x114
store i32 0,i32* %x116
br label %x73
x73:
%x117 = load i32, i32* %x28
%x118 = add i32 %x117,1
store i32 %x118,i32* %x28
br label %x29
x36:
ret void
}
define dso_local i32 @main(){
%x119=alloca i32
%x120= call i32 @getint()
store i32 %x120,i32* %x119
br label %x121
x121:
%x122 = load i32, i32* %x119
%x123 = icmp sgt i32 %x122,0
%x124 = zext i1 %x123 to i32
%x125= icmp ne i32 %x124, 0
br i1 %x125, label %x126, label %x127
x126:
%x128= call i32 @getint()
store i32 %x128,i32* @n
call void @f(i32 1)
%x129 = load i32, i32* %x119
%x130 = sub i32 %x129,1
store i32 %x130,i32* %x119
br label %x121
x127:
ret i32 0
ret i32 0
}
