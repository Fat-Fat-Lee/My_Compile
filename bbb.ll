declare i32 @getint()
declare i32 @getch()
declare i32 @getarray(i32*)
declare void @putint(i32)
declare void @putch(i32)
declare void @putarray(i32, i32*)
declare void @memset(i32*, i32, i32)
define dso_local i32 @main(){
%x1=alloca [8 x i32]
%x2 = getelementptr [8 x i32],[8 x i32]* %x1, i32 0, i32 0
call void @memset(i32* %x2, i32 0, i32 32)
%x3 = getelementptr [8 x i32], [8 x i32]* %x1, i32 0, i32 0
%x4=getelementptr i32,i32* %x3, i32 0
store i32 1, i32* %x4
%x5=getelementptr i32,i32* %x3, i32 1
store i32 2, i32* %x5
%x6=getelementptr i32,i32* %x3, i32 2
store i32 3, i32* %x6
%x7=getelementptr i32,i32* %x3, i32 3
store i32 4, i32* %x7
%x8=getelementptr i32,i32* %x3, i32 4
store i32 5, i32* %x8
%x9=getelementptr i32,i32* %x3, i32 5
store i32 6, i32* %x9
%x10=getelementptr i32,i32* %x3, i32 6
store i32 7, i32* %x10
%x11=getelementptr i32,i32* %x3, i32 7
store i32 8, i32* %x11
%x12=alloca i32
store i32 3,i32* %x12
%x13=alloca [8 x i32]
%x14 = getelementptr [8 x i32],[8 x i32]* %x13, i32 0, i32 0
call void @memset(i32* %x14, i32 0, i32 32)
%x15 = getelementptr [8 x i32], [8 x i32]* %x13, i32 0, i32 0
%x16=getelementptr i32,i32* %x15, i32 0
store i32 1, i32* %x16
%x17=getelementptr i32,i32* %x15, i32 1
store i32 2, i32* %x17
%x18=getelementptr i32,i32* %x15, i32 2
store i32 3, i32* %x18
%x19=getelementptr i32,i32* %x15, i32 3
store i32 4, i32* %x19
%x20=getelementptr i32,i32* %x15, i32 4
store i32 5, i32* %x20
%x21=getelementptr i32,i32* %x15, i32 5
store i32 6, i32* %x21
%x22=getelementptr i32,i32* %x15, i32 6
store i32 7, i32* %x22
%x23=getelementptr i32,i32* %x15, i32 7
store i32 8, i32* %x23
%x25 = getelementptr [8 x i32], [8 x i32]* %x1, i32 0, i32 0
%x27 = mul i32 3, 2
%x28 = mul i32 0, 1
%x29 = add i32 %x27, 0
%x30 = add i32 %x28, %x29
%x26=getelementptr i32,i32* %x25, i32 %x30
%x31=load i32,i32* %x26
%x32=alloca [8 x i32]
%x33 = getelementptr [8 x i32],[8 x i32]* %x32, i32 0, i32 0
call void @memset(i32* %x33, i32 0, i32 32)
%x34 = getelementptr [8 x i32], [8 x i32]* %x32, i32 0, i32 0
%x35=getelementptr i32,i32* %x34, i32 0
store i32 1, i32* %x35
%x36=getelementptr i32,i32* %x34, i32 1
store i32 2, i32* %x36
%x37=getelementptr i32,i32* %x34, i32 2
store i32 3, i32* %x37
%x38=getelementptr i32,i32* %x34, i32 3
store i32 0, i32* %x38
%x39=getelementptr i32,i32* %x34, i32 4
store i32 5, i32* %x39
%x40=getelementptr i32,i32* %x34, i32 5
store i32 0, i32* %x40
%x41=getelementptr i32,i32* %x34, i32 6
store i32 %x31, i32* %x41
%x42=getelementptr i32,i32* %x34, i32 7
store i32 8, i32* %x42
%x43 = getelementptr [8 x i32], [8 x i32]* %x32, i32 0, i32 0
%x45 = mul i32 2, 2
%x46 = mul i32 1, 1
%x47 = add i32 %x45, 0
%x48 = add i32 %x46, %x47
%x44=getelementptr i32,i32* %x43, i32 %x48
%x49=load i32,i32* %x44
%x50 = getelementptr [8 x i32], [8 x i32]* %x13, i32 0, i32 0
%x52 = mul i32 2, 2
%x53 = mul i32 1, 1
%x54 = add i32 %x52, 0
%x55 = add i32 %x53, %x54
%x51=getelementptr i32,i32* %x50, i32 %x55
%x56=load i32,i32* %x51
%x57=alloca [8 x i32]
%x58 = getelementptr [8 x i32],[8 x i32]* %x57, i32 0, i32 0
call void @memset(i32* %x58, i32 0, i32 32)
%x59 = getelementptr [8 x i32], [8 x i32]* %x57, i32 0, i32 0
%x60=getelementptr i32,i32* %x59, i32 0
store i32 %x49, i32* %x60
%x61=getelementptr i32,i32* %x59, i32 1
store i32 %x56, i32* %x61
%x62=getelementptr i32,i32* %x59, i32 2
store i32 3, i32* %x62
%x63=getelementptr i32,i32* %x59, i32 3
store i32 4, i32* %x63
%x64=getelementptr i32,i32* %x59, i32 4
store i32 5, i32* %x64
%x65=getelementptr i32,i32* %x59, i32 5
store i32 6, i32* %x65
%x66=getelementptr i32,i32* %x59, i32 6
store i32 7, i32* %x66
%x67=getelementptr i32,i32* %x59, i32 7
store i32 8, i32* %x67
%x68 = getelementptr [8 x i32], [8 x i32]* %x57, i32 0, i32 0
%x70 = mul i32 3, 2
%x71 = mul i32 1, 1
%x72 = mul i32 0, 1
%x73 = add i32 %x70, 0
%x74 = add i32 %x71, %x73
%x75 = add i32 %x72, %x74
%x69=getelementptr i32,i32* %x68, i32 %x75
%x76=load i32,i32* %x69
%x77 = getelementptr [8 x i32], [8 x i32]* %x57, i32 0, i32 0
%x79 = mul i32 0, 2
%x80 = mul i32 0, 1
%x81 = mul i32 0, 1
%x82 = add i32 %x79, 0
%x83 = add i32 %x80, %x82
%x84 = add i32 %x81, %x83
%x78=getelementptr i32,i32* %x77, i32 %x84
%x85=load i32,i32* %x78
%x86 = getelementptr [8 x i32], [8 x i32]* %x57, i32 0, i32 0
%x88 = mul i32 0, 2
%x89 = mul i32 1, 1
%x90 = mul i32 0, 1
%x91 = add i32 %x88, 0
%x92 = add i32 %x89, %x91
%x93 = add i32 %x90, %x92
%x87=getelementptr i32,i32* %x86, i32 %x93
%x94=load i32,i32* %x87
%x95 = getelementptr [8 x i32], [8 x i32]* %x32, i32 0, i32 0
%x97 = mul i32 3, 2
%x98 = mul i32 0, 1
%x99 = add i32 %x97, 0
%x100 = add i32 %x98, %x99
%x96=getelementptr i32,i32* %x95, i32 %x100
%x101=load i32,i32* %x96
%x102 = add i32 %x76,%x85
%x103 = add i32 %x102,%x94
%x104 = add i32 %x103,%x101
call void @putint(i32 %x104)
ret i32 0
ret i32 0
}
