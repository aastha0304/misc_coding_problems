#include <iostream>
#include <algorithm>
#include <limits.h>
 
using namespace std ;
float p[1001];
int d[1001];
void display(int a[],int n)
{
                 for(int i=0;i<=n;i++)
                         cout<<a[i]<<'\t';
                 cout<<endl;
}
                 void display(float a[],int n)
{
                 for(int i=0;i<=n;i++)
                 cout<<a[i]<<'\t';
                 cout<<endl;
                 }
float jump(float p[],int d[], int n,int L,int D) {
        // Start typing your C/C++ solution below
        // DO NOT write int main() function
        float pr[1001];
        int i=0,j;
        for(i=n;i>=0;i--)
        {
                            if(d[i]+L>=D)
                                         pr[i]=p[i];
                            else
                            {
                                float t=0;
                                         for(j=i+1;d[j]-d[i]<=L && j<=n;j++)
                                         {
                                                               if(t<pr[j])
                                                               t=pr[j];     
                                          }
                                          pr[i]=p[i]*t;
                            }
         }
         //display(pr,n);
         return pr[0];
}
 
int main(){
int n,L,D ;
cin>>n>>L>>D ;
p[0]=1;
for(int i=1; i<=n ; i++){
cin>>p[i];
}
 
d[0]=0;
for(int i=1; i<=n ; i++){
cin>>d[i];
}
 
 
printf("%.6f",jump( p,d,n,L,D)) ;
system("pause");
return 0;
} 
