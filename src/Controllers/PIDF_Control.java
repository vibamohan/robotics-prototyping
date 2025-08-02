package Controllers;

import edu.wpi.first.math.geometry.Translation2d;

// WPILib Units API (2025+)
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.LinearVelocity;
import edu.wpi.first.units.LinearAcceleration;
import edu.wpi.first.units.Time;
import static edu.wpi.first.units.Units.*; 

import java.util.function.BiFunction;


class Gains {
  static class PID {
    double KP;
    double KI;
    double KD;
  }

  static class FF {
    double KS;
    double KG;
    double KV;
    double KA;
  }
}

class MotionProfiles {
  static class Constraints {
    LinearVelocity    maxVelocity;
    LinearAcceleration maxAcceleration;
    double             maxJerk; 

    public Constraints(
        LinearVelocity maxVelocity,
        LinearAcceleration maxAcceleration,
        double maxJerk) {
      this.maxVelocity     = maxVelocity;
      this.maxAcceleration = maxAcceleration;
      this.maxJerk         = maxJerk;
    }
  }

  static class ProfileOutputs {
    Translation2d      position;
    LinearVelocity     velocity;
    LinearAcceleration acceleration;

    public ProfileOutputs(
        Translation2d position,
        LinearVelocity velocity,
        LinearAcceleration acceleration) {
      this.position     = position;
      this.velocity     = velocity;
      this.acceleration = acceleration;
    }
  }

  private static LinearVelocity S(Constraints c, Time dt) {
    double t = dt.in(Seconds);
    double pos = 0.5 * c.maxAcceleration.in(MetersPerSecondSquared) * t * t;
    double vel = Math.min(pos / t, c.maxVelocity.in(MetersPerSecond));
    return MetersPerSecond.of(vel);
  }

  private static LinearVelocity trapezoid(Constraints c, Time dt) {
    double t         = dt.in(Seconds);
    double maxV      = c.maxVelocity.in(MetersPerSecond);
    double maxA      = c.maxAcceleration.in(MetersPerSecondSquared);
    double accelTime = maxV / maxA;
    double pos;

    if (t < accelTime) {
      pos = 0.5 * maxA * t * t;
    } else {
      pos = 0.5 * maxA * accelTime * accelTime + maxV * (t - accelTime);
    }

    double vel = pos / t;
    return MetersPerSecond.of(vel);
  }

  enum Profiles {
    S_CURVE(MotionProfiles::S),
    TRAPEZOID(MotionProfiles::trapezoid);

    private final BiFunction<Constraints, Time, LinearVelocity> profile;

    Profiles(BiFunction<Constraints, Time, LinearVelocity> profile) {
      this.profile = profile;
    }

    public LinearVelocity compute(Constraints c, Time dt) {
      return profile.apply(c, dt);
    }
  }
}

interface FF {
  LinearAcceleration calculate(LinearVelocity velocity);
}

class PID_Controller {}

class Feedforward_Controller {}

class PIDF {
  MotionProfiles.Profiles profile;
  Gains                    gains;

  public PIDF(MotionProfiles.Profiles profile, Gains gains) {
    this.profile = profile;
    this.gains   = gains;
  }

  public void computeNextTimestamp() {}
}

public class PIDF_Control {
  public static void main(String[] args) {
    System.out.println("Hello, world!");
  }
}
