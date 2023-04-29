#include "triangle.h"
#include <algorithm>
#include <stdexcept>

namespace triangle {

flavor kind(double a, double b, double c) {
  if (a <= 0 || b <= 0 || c <= 0) {
    throw std::domain_error("Invalid triangle.");
  }
  auto v = std::vector({a, b, c});
  std::sort(v.begin(), v.end());
  if (v[0] + v[1] < v[2]) {
    throw std::domain_error("Invalid triangle");
  }
  if (a == b && b == c) {
    return flavor::equilateral;
  }
  if (a == b || b == c || a == c) {
    return flavor::isosceles;
  }
  return flavor::scalene;
}

}  // namespace triangle
