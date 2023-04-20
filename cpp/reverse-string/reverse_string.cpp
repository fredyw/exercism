#include "reverse_string.h"
#include <algorithm>

namespace reverse_string {

std::string reverse_string(std::string_view str) {
  return {str.rbegin(), str.rend()};
}

} // namespace reverse_string
