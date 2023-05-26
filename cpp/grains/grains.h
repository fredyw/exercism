#if !defined(GRAINS_H)
#define GRAINS_H

#include <cstdint>
#include <limits>

namespace grains {

constexpr unsigned long long square(int square) {
  return 0x1ULL << (square - 1);
}

constexpr unsigned long long total() {
  return std::numeric_limits<uint64_t>::max();
}

}  // namespace grains

#endif // GRAINS_H