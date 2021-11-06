declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
define dso_local i32 @main(){
%1=alloca i32
%2=alloca i32
%3=alloca i32
%4=alloca i32
%5=alloca i32
store i32 5,i32* %1
store i32 5,i32* %2
store i32 1,i32* %3
%6 = sub i32 0,2
store i32 %6,i32* %4
store i32 2,i32* %5
br label %7
7:
%8 = load i32, i32* %4
%9 = load i32, i32* %1
%10 = load i32, i32* %2
%11 = load i32, i32* %3
%12 = mul i32 %8,1
%13 = sdiv i32 %12,2
%14 = icmp slt i32 %13,0
%15 = zext i1 %14 to i32
%16 = sub i32 %9,%10
%17 = icmp ne i32 %16,0
%18 = zext i1 %17 to i32
%19 = add i32 %11,3
%20 = srem i32 %19,2
%21 = icmp ne i32 %20,0
%22 = zext i1 %21 to i32
%23 = and i32 %18,%22
%24 = or i32 %15,%23
%25= icmp ne i32 %24, 0
br i1 %25,label %26, label %28
26:
%27 = load i32, i32* %5
store i32 %27,i32* %5
br label %28
28:
br label %29
29:
%30 = load i32, i32* %4
%31 = load i32, i32* %1
%32 = load i32, i32* %2
%33 = load i32, i32* %3
%34 = srem i32 %30,2
%35 = add i32 %34,67
%36 = icmp slt i32 %35,0
%37 = zext i1 %36 to i32
%38 = sub i32 %31,%32
%39 = icmp ne i32 %38,0
%40 = zext i1 %39 to i32
%41 = add i32 %33,2
%42 = srem i32 %41,2
%43 = icmp ne i32 %42,0
%44 = zext i1 %43 to i32
%45 = and i32 %40,%44
%46 = or i32 %37,%45
%47= icmp ne i32 %46, 0
br i1 %47,label %48, label %49
48:
store i32 4,i32* %5
br label %49
49:
%50 = load i32, i32* %5
ret i32 %50
}
