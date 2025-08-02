load("@rules_java//java:defs.bzl", "java_binary")

java_binary(
    name       = "pidf_control",
    srcs       = glob(["src/Controllers/*.java"]),
    main_class = "Controllers.PIDF_Control",
    deps = [
        "@maven//:edu_wpi_first_wpilibj_wpilibj_java",
        "@maven//:edu_wpi_first_wpimath_wpimath_java",
        "@maven//:edu_wpi_first_wpiunits_wpiunits_java",
    ],
)
