declare i32 @getint()
declare i32 @getch()
declare i32 @getarray(i32*)
declare void @putint(i32)
declare void @putch(i32)
declare void @putarray(i32, i32*)
declare void @memset(i32*, i32, i32)
define dso_local i32 @func1( i32 %x1 , i32 %x2 , i32 %x3 ){
%x4 = alloca i32 
store i32 %x1, i32* %x4
%x5 = alloca i32 
store i32 %x2, i32* %x5
%x6 = alloca i32 
store i32 %x3, i32* %x6
%x7 = load i32, i32* %x6
%x8 = icmp eq i32 %x7,0
%x9 = zext i1 %x8 to i32
%x10= icmp ne i32 %x9, 0
br i1 %x10, label %x11, label %x12
x11:
%x13 = load i32, i32* %x4
%x14 = load i32, i32* %x5
%x15 = mul i32 %x13,%x14
ret i32 %x15

x12:
%x17 = load i32, i32* %x4
%x18 = load i32, i32* %x5
%x19 = load i32, i32* %x6
%x20 = sub i32 %x18,%x19
%x21= call i32 @func1(i32 %x17,i32 %x20,i32 0)
ret i32 %x21

x16:
ret i32 0
}
define dso_local i32 @func2( i32 %x22 , i32 %x23 ){
%x24 = alloca i32 
store i32 %x22, i32* %x24
%x25 = alloca i32 
store i32 %x23, i32* %x25
%x26 = load i32, i32* %x25
%x27= icmp ne i32 %x26, 0
br i1 %x27, label %x28, label %x29
x28:
%x30 = load i32, i32* %x24
%x31 = load i32, i32* %x25
%x32 = srem i32 %x30,%x31
%x33= call i32 @func2(i32 %x32,i32 0)
ret i32 %x33

x29:
%x35 = load i32, i32* %x24
ret i32 %x35

x34:
ret i32 0
}
define dso_local i32 @func3( i32 %x36 , i32 %x37 ){
%x38 = alloca i32 
store i32 %x36, i32* %x38
%x39 = alloca i32 
store i32 %x37, i32* %x39
%x40 = load i32, i32* %x39
%x41 = icmp eq i32 %x40,0
%x42 = zext i1 %x41 to i32
%x43= icmp ne i32 %x42, 0
br i1 %x43, label %x44, label %x45
x44:
%x46 = load i32, i32* %x38
%x47 = add i32 %x46,1
ret i32 %x47

x45:
%x49 = load i32, i32* %x38
%x50 = load i32, i32* %x39
%x51 = add i32 %x49,%x50
%x52= call i32 @func3(i32 %x51,i32 0)
ret i32 %x52

x48:
ret i32 0
}
define dso_local i32 @func4( i32 %x53 , i32 %x54 , i32 %x55 ){
%x56 = alloca i32 
store i32 %x53, i32* %x56
%x57 = alloca i32 
store i32 %x54, i32* %x57
%x58 = alloca i32 
store i32 %x55, i32* %x58
%x59 = load i32, i32* %x56
%x60= icmp ne i32 %x59, 0
br i1 %x60, label %x61, label %x62
x61:
%x63 = load i32, i32* %x57
ret i32 %x63

x62:
%x65 = load i32, i32* %x58
ret i32 %x65

x64:
ret i32 0
}
define dso_local i32 @func5( i32 %x66 ){
%x67 = alloca i32 
store i32 %x66, i32* %x67
%x68 = load i32, i32* %x67
%x69 = sub i32 0,%x68
ret i32 %x69
ret i32 0
}
define dso_local i32 @func6( i32 %x70 , i32 %x71 ){
%x72 = alloca i32 
store i32 %x70, i32* %x72
%x73 = alloca i32 
store i32 %x71, i32* %x73
%x74 = load i32, i32* %x72
%x75 = load i32, i32* %x73
%x76= icmp ne i32 %x75, 0
%x77 = zext i1 %x76 to i32
%x78= icmp ne i32 %x74, 0
%x79 = zext i1 %x78 to i32
%x80 = and i32 %x79,%x77
%x81= icmp ne i32 %x80, 0
br i1 %x81, label %x82, label %x83
x82:
ret i32 1

x83:
ret i32 0

x84:
ret i32 0
}
define dso_local i32 @func7( i32 %x85 ){
%x86 = alloca i32 
store i32 %x85, i32* %x86
%x87 = load i32, i32* %x86
%x88 = icmp eq i32 %x87,0
%x89 = zext i1 %x88 to i32
%x90= icmp ne i32 %x89, 0
br i1 %x90, label %x91, label %x92
x91:
ret i32 1

x92:
ret i32 0

x93:
ret i32 0
}
define dso_local i32 @main(){
%x94=alloca i32
%x95= call i32 @getint()
store i32 %x95,i32* %x94
%x96=alloca i32
%x97= call i32 @getint()
store i32 %x97,i32* %x96
%x98=alloca i32
%x99= call i32 @getint()
store i32 %x99,i32* %x98
%x100=alloca i32
%x101= call i32 @getint()
store i32 %x101,i32* %x100
%x102=alloca [10 x i32]
%x103 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
call void @memset(i32* %x103, i32 0, i32 40)
%x104=alloca i32
store i32 0,i32* %x104
br label %x105
x105:
%x106 = load i32, i32* %x104
%x107 = icmp slt i32 %x106,10
%x108 = zext i1 %x107 to i32
%x109= icmp ne i32 %x108, 0
br i1 %x109, label %x110, label %x111
x110:
%x112 = load i32, i32* %x104
%x113 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x114=getelementptr i32,i32* %x113, i32 %x112
%x115= call i32 @getint()
store i32 %x115,i32* %x114
%x116 = load i32, i32* %x104
%x117 = add i32 %x116,1
store i32 %x117,i32* %x104
br label %x105
x111:
%x118=alloca i32
%x119 = load i32, i32* %x94
%x120 = load i32, i32* %x96
%x121 = load i32, i32* %x98
%x122 = load i32, i32* %x100
%x123 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x124=getelementptr i32,i32* %x123, i32 0
%x125=load i32,i32* %x124
%x126 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x127=getelementptr i32,i32* %x126, i32 1
%x128=load i32,i32* %x127
%x129 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x130=getelementptr i32,i32* %x129, i32 2
%x131=load i32,i32* %x130
%x132 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x133=getelementptr i32,i32* %x132, i32 3
%x134=load i32,i32* %x133
%x135 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x136=getelementptr i32,i32* %x135, i32 4
%x137=load i32,i32* %x136
%x138 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x139=getelementptr i32,i32* %x138, i32 5
%x140=load i32,i32* %x139
%x141 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x142=getelementptr i32,i32* %x141, i32 6
%x143=load i32,i32* %x142
%x144 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x145=getelementptr i32,i32* %x144, i32 7
%x146=load i32,i32* %x145
%x147 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x148=getelementptr i32,i32* %x147, i32 8
%x149=load i32,i32* %x148
%x150 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x151=getelementptr i32,i32* %x150, i32 9
%x152=load i32,i32* %x151
%x153 = load i32, i32* %x94
%x154 = load i32, i32* %x96
%x155 = load i32, i32* %x98
%x156 = load i32, i32* %x100
%x157 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x158=getelementptr i32,i32* %x157, i32 0
%x159=load i32,i32* %x158
%x160 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x161=getelementptr i32,i32* %x160, i32 1
%x162=load i32,i32* %x161
%x163 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x164=getelementptr i32,i32* %x163, i32 2
%x165=load i32,i32* %x164
%x166 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x167=getelementptr i32,i32* %x166, i32 3
%x168=load i32,i32* %x167
%x169 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x170=getelementptr i32,i32* %x169, i32 4
%x171=load i32,i32* %x170
%x172 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x173=getelementptr i32,i32* %x172, i32 5
%x174=load i32,i32* %x173
%x175 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x176=getelementptr i32,i32* %x175, i32 6
%x177=load i32,i32* %x176
%x178 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x179=getelementptr i32,i32* %x178, i32 7
%x180=load i32,i32* %x179
%x181 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x182=getelementptr i32,i32* %x181, i32 8
%x183=load i32,i32* %x182
%x184 = getelementptr [10 x i32], [10 x i32]* %x102, i32 0, i32 0
%x185=getelementptr i32,i32* %x184, i32 9
%x186=load i32,i32* %x185
%x187 = load i32, i32* %x94
%x188= call i32 @func7(i32 %x119)
%x189= call i32 @func5(i32 %x120)
%x190= call i32 @func6(i32 %x188,i32 %x189)
%x191= call i32 @func2(i32 %x190,i32 %x121)
%x192= call i32 @func3(i32 %x191,i32 %x122)
%x193= call i32 @func5(i32 %x192)
%x194= call i32 @func5(i32 %x128)
%x195= call i32 @func7(i32 %x134)
%x196= call i32 @func6(i32 %x131,i32 %x195)
%x197= call i32 @func7(i32 %x140)
%x198= call i32 @func2(i32 %x137,i32 %x197)
%x199= call i32 @func4(i32 %x194,i32 %x196,i32 %x198)
%x200= call i32 @func3(i32 %x199,i32 %x143)
%x201= call i32 @func2(i32 %x200,i32 %x146)
%x202= call i32 @func7(i32 %x152)
%x203= call i32 @func3(i32 %x149,i32 %x202)
%x204= call i32 @func1(i32 %x201,i32 %x203,i32 %x153)
%x205= call i32 @func4(i32 %x193,i32 %x125,i32 %x204)
%x206= call i32 @func7(i32 %x155)
%x207= call i32 @func3(i32 %x206,i32 %x156)
%x208= call i32 @func2(i32 %x154,i32 %x207)
%x209= call i32 @func3(i32 %x205,i32 %x208)
%x210= call i32 @func1(i32 %x209,i32 %x159,i32 %x162)
%x211= call i32 @func2(i32 %x210,i32 %x165)
%x212= call i32 @func5(i32 %x174)
%x213= call i32 @func3(i32 %x171,i32 %x212)
%x214= call i32 @func5(i32 %x177)
%x215= call i32 @func2(i32 %x213,i32 %x214)
%x216= call i32 @func7(i32 %x183)
%x217= call i32 @func1(i32 %x215,i32 %x180,i32 %x216)
%x218= call i32 @func5(i32 %x186)
%x219= call i32 @func2(i32 %x217,i32 %x218)
%x220= call i32 @func3(i32 %x219,i32 %x187)
%x221= call i32 @func1(i32 %x211,i32 %x168,i32 %x220)
store i32 %x221,i32* %x118
%x222 = load i32, i32* %x118
call void @putint(i32 %x222)
ret i32 0
ret i32 0
}
