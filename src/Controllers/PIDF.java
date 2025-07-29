class PIDF_CONFIG {
      static class Gains {
    static class PID {
      double KP;
      double KI;
      double kD;
    }

    static class FF {
      double KS;
      double KG;
      double KV;
      double KA;
    }
  }

  sealed class Constraints {
    
  }

  enum Profiles {
    S_CURVE((Constraints c, ) -> {});
    Profiles(Function<Double, Double> profile) {
        this.profile = profile;
    }
    
    Function<Double, Double> profile;
  }

}

public class PIDF {
  public computeNextTimestamp() {

  }
}
