define dso_local i32 @main(){
%x0 = sub i32 0,2
%x1 = sub i32 4,5
%x2 = sdiv i32 3,%x1
%x3 = mul i32 %x0,%x2
%x4 = add i32 1,%x3
ret i32 %x4
}
