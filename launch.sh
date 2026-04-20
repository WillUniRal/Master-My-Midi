# jar --create --file app.jar --manifest MANIFEST.MF -C classes . -C src/main/resources .

/home/silent/.jdks/temurin-24.0.2/bin/java --module-path lib:app.jar --add-modules=ALL-MODULE-PATH -m uk.ac.bucks.willralph.mmmidi/uk.ac.bucks.willralph.mmmidi.Launcher
