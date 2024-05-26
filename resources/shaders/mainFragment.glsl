#version 330 core

in vec3 passColor;
in vec2 passTextureCoord;

out vec4 outColor;

uniform sampler2D tex;

void main() {
	  vec4 textureColor = texture(tex, passTextureCoord);
	  vec4 color = vec4(passColor, 1.0); // Convert passColor to a vec4 with alpha set to 1.0
      outColor = textureColor * color;
}