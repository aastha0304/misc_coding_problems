#include <iostream>
#include <algorithm>
#include <limits.h>
 
using namespace std ;
 
int a[101];
int main()
{
int t ;
cin>>t;
int n ;
while(t--){
int n ;
cin>>n ;
for(int i=0; i<n; i++){
cin>>a[i];
}
int min = INT_MAX ;
int max= INT_MIN ;
/*pair each one with each and every other else */
sort(a, a+n);
//cout<<(a[n-1]+a[0]) - (a[n/2]+a[n/2-1])<<endl;
for(int i=0,j=n-1; i<j ; i++,j--){
int p = a[i] + a[j];
if(p>max)
max = p;
if(p<min)
min=p;
}
cout<<(max-min)<<endl;
}
system("pause");
return 0; 
} 
