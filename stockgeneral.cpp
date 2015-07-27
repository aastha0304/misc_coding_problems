#include <iostream>
using namespace std;
int max(int a, int b){
    return a>b?a:b;
}
void display(int *a, int n){
    for(int i =0;i<n;i++)
        cout<<a[i]<<" ";
    cout<<'\n';
}
int main(){
    int a[] = {8,9,10,11,12,13,14,15,16,17};
    int n = 10;
    int *p = new int[n];
    int i;
    for(i = 0;i<n;i++){
        p[i]=0;
    }
    int maxp = a[n-1];
    for(i = n-2;i>=0;i--){
        if(a[i]>maxp)
        {
            maxp = a[i];
        }
        p[i]=max(p[i+1], maxp-a[i]);
    }
    display(p, n);
    int minp = a[0];
    for(i=1;i<n;i++){
        if(a[i]<minp)
            minp=a[i];
        p[i]=max(p[i-1], p[i]+a[i]-minp);
    }
    cout<<p[i-1];
}