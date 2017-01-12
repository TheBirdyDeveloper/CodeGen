#include <lua.h>
#include <lauxlib.h>
#include <lualib.h>

#include <stdlib.h>
#include <stdio.h>

// Compiler avec : gcc -o luaLauncher luaLauncher.c -Wall -I/usr/include/lua5.2 -llua5.2
void bail(lua_State *L, char *msg){
	fprintf(stderr, "\nFATAL ERROR:\n   %s: %s\n\n", msg,lua_tostring(L,-1));
	exit(1);
}

int main(int argc, char ** argv)
{
	lua_State *L;
	
	L = luaL_newstate();				/* Create Lua state variable */
	luaL_openlibs(L);				/* Load Lua libraries  */

	if(luaL_loadfile(L,argv[1]))		/* Load but don't run the Lua script  */
		bail(L,"luaL_loadfile() failed");	/* Error out if file can't be read  */

	if(lua_pcall(L,0,0,0))				/* PRIMING RUN. FORGET THIS AND YOU'RE TOAST  */
		bail(L,"lua_pcall() failed");		/* Error out if Lua file has an error  */

	lua_close(L);					/* Clean up, free the Lua state var  */

	return 0;
}
