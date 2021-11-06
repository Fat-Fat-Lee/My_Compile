declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
define dso_local i32 @main(){
%1=alloca i32
%2=alloca i32
%3=alloca i32
store i32 1,i32* %3
%4=alloca i32
%5=alloca i32
store i32 5,i32* %1
store i32 5,i32* %2
%6 = sub i32 0,2
store i32 %6,i32* %4
store i32 2,i32* %5
br label %7
7:
%8 = load i32, i32* %1
%9 = load i32, i32* %2
%10 = load i32, i32* %1
%11 = load i32, i32* %2
%12 = load i32, i32* %5
%13 = add i32 %8,%9
%14 = icmp eq i32 %13,9
%15 = zext i1 %14 to i32
%16 = sub i32 %10,%11
%17 = icmp eq i32 %16,0
%18 = zext i1 %17 to i32
%19 = icmp ne i32 %12,4
%20 = zext i1 %19 to i32
%21 = and i32 %18,%20
%22 = or i32 %15,%21
%23= icmp ne i32 %22, 0
br i1 %23,label %24, label %27
24:
%25 = load i32, i32* %5
%26 = add i32 %25,3
store i32 %26,i32* %5
br label %44
27:
%28 = load i32, i32* %3
%29 = load i32, i32* %4
%30 = load i32, i32* %5
%31 = add i32 %28,%29
%32 = sub i32 0,1
%33 = icmp ne i32 %31,%32
%34 = zext i1 %33 to i32
%35 = add i32 %30,1
%36 = srem i32 %35,2
%37 = icmp eq i32 %36,1
%38 = zext i1 %37 to i32
%39 = or i32 %34,%38
%40= icmp ne i32 %39, 0
br i1 %40,label %41, label %44
41:
%42 = load i32, i32* %5
%43 = add i32 %42,4
store i32 %43,i32* %5
br label %44

44:
%45 = load i32, i32* %5
ret i32 %45
}
