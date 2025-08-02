load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# rules_jvm_external
http_archive(
    name         = "rules_jvm_external",
    url          = "https://github.com/bazelbuild/rules_jvm_external/archive/refs/tags/4.5.zip",
    strip_prefix = "rules_jvm_external-4.5",
    # (add sha256 here if you like)
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    name = "maven",
    artifacts = [
        "edu.wpi.first.wpilibj:wpilibj-java:2025.3.2",
        "edu.wpi.first.wpimath:wpimath-java:2025.3.2",
        "edu.wpi.first.wpiunits:wpiunits-java:2025.3.2",
    ],
    repositories = [
        "https://frcmaven.wpi.edu/artifactory/release",
        "https://repo1.maven.org/maven2",
    ],
)
