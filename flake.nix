{
  description = "Development shell for PP Lab 7";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs {
          inherit system;
        };
        jdk = pkgs.jdk21;
      in {
        devShells.default = pkgs.mkShell {
          packages = with pkgs; [
            jdk
            maven
          ];

          shellHook = ''
            export JAVA_HOME=${jdk}
            export MAVEN_OPTS="-Dmaven.compiler.release=21"
          '';
        };
      });
}