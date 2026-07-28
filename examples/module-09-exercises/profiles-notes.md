| Question | Your answer |
| -------- |-------------|
| Which profile is active when you run plain `mvn package`? | dev         |
| How do you activate `prod` on the command line? | mvn -Pprod  |
| What is the `app.env` value under `dev`? | dev         |
| What is the `app.env` value under `prod`? | prod        |


putting production database passwords inside the dev profile; - this is not secure and now anybody with dev access has db passwords

making prod activeByDefault on every engineer laptop; - it is dangerous to run prod and possibly overwrite prod data when developing

assuming profiles change Java package names (they do not — they change build/config properties); -they don't change this and if you change the package name because of this, you will not be able to compile

documenting secrets in screenshots of profile properties. - this is insecure and can leak passwords, same as example 1

Keep `dev` as the laptop default.
Activate `prod` intentionally with `-Pprod`.
Never store real production secrets in `pom.xml` profiles.