#include <lua.h>
#include <lauxlib.h>
#include <lualib.h>

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// Compiler avec : gcc -o luaLauncher luaLauncher.c -Wall -I/usr/include/lua5.2 -llua5.2
void bail(lua_State *L, char *msg){
	fprintf(stderr, "\nFATAL ERROR:\n   %s: %s\n\n", msg,lua_tostring(L,-1));
	exit(1);
}

char *concat_string(const char *s1, const char *s2)
{
     char *s3=NULL;
     s3=(char *)malloc((strlen(s1)+strlen(s2))+1);
     strcpy(s3,s1);
     strcat(s3,s2);
     return s3;
}

char * recupParams(char * params[], char * nameFile){
   char * res;
	 int i = 2;
   if(params[2] == NULL){
      res = "sed s/PARAMS/''/g ";
   }else{
      res = "sed s/PARAMS/'";
			while(params[i]!= NULL){
				res = concat_string(res, params[i]);
				i++;
				if(params[i] != NULL){
					res = concat_string(res,", ");
				}
			}

      res = concat_string(res, "'/g ");
   }

   res = concat_string(res, nameFile);
   res = concat_string(res, " > temp.lua");
   return res;
}

void exec_fic(char* com_params){
	system(com_params);

}

void clean(){
	system("rm temp.lua");
}


int main(int argc, char ** argv)
{
	char * params = recupParams(argv, argv[1]);
	exec_fic(params);
	free(params);

	lua_State *L;

	L = luaL_newstate();				/* Create Lua state variable */
	luaL_openlibs(L);				/* Load Lua libraries  */

	if(luaL_loadfile(L,"temp.lua"))		/* Load but don't run the Lua script  */
		bail(L,"luaL_loadfile() failed");	/* Error out if file can't be read  */

	if(lua_pcall(L,0,0,0))				/* PRIMING RUN. FORGET THIS AND YOU'RE TOAST  */
		bail(L,"lua_pcall() failed");		/* Error out if Lua file has an error  */

	lua_close(L);					/* Clean up, free the Lua state var  */
	clean();
	return 0;
}
