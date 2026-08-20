#include<iostream>
using namespace std;

void display(int n){
    if(n>0){
        cout<<n<<endl;
        display(n-1);
    }
}
int main(){
    int n=11;
    display(n);
    return 0;
}