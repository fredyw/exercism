#if !defined(SPACE_AGE_H)
#define SPACE_AGE_H

namespace space_age {

constexpr int SECONDS_IN_EARTH = 31557600;

class space_age {
public:
  explicit space_age(long seconds) : seconds_(seconds) {}

  constexpr long seconds() const { return seconds_; }

  constexpr double on_earth() const { return calculate(1); }

  constexpr double on_mercury() const { return calculate(0.2408467); }

  constexpr double on_venus() const { return calculate(0.61519726); }

  constexpr double on_mars() const { return calculate(1.8808158); }

  constexpr double on_jupiter() const { return calculate(11.862615); }

  constexpr double on_saturn() const { return calculate(29.447498); }

  constexpr double on_uranus() const { return calculate(84.016846); }

  constexpr double on_neptune() const { return calculate(164.79132); }

private:
  long seconds_;

  constexpr double calculate(double years) const {
    return seconds_ / years / SECONDS_IN_EARTH;
  }
};

} // namespace space_age

#endif // SPACE_AGE_H