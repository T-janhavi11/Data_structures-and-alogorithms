#include <iostream>
#include <vector>

using std::cin;
using std::cout;
using std::vector;
using std::max;

int compute_min_refills(int dist, int tank, vector<int> & stops) 
{

 int currRef=0;
 //int ref=0;
 int numRef=0;
 int n = stops.size();
 while(currRef < n-1)
 {
  int lastRef = currRef;
  
  while(currRef < n+1 && (stops[currRef+1] - stops[lastRef] <= tank))
   currRef++;
  
  if(currRef == lastRef)
   return -1;
  if(currRef < n-1)
   numRef++;
 }

 return numRef;
}


int main() {
    int d = 0;
    cin >> d;
    int m = 0;
    cin >> m;
    int n = 0;
    cin >> n;

    vector<int> stops(n+2);
    stops[0] = 0;
    for (size_t i = 0; i < n; ++i)
        cin >> stops.at(i+1);

    stops[n+1] = d;

    cout << compute_min_refills(d, m, stops) << "\n";

    return 0;
}
