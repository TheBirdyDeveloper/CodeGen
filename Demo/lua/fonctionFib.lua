require('luaLib/print')
require('luaLib/equals')
require('luaLib/tl')
function F6(X1)
   X1=tl(X1)

return X1
end
function F2(X5, X6)
   X4=X5
   X7=X6
   while X7 do
            X4=F4(X4)
      X7 = X7.tl --Décrémentation de la condition du For
   end

return X4
end
function F4(X10)
   X10={ hd = nil, tl = X10 }

return X10
end
function F5(X12)
   if X12 then 
      X11=nil
   else 
      X11={ hd = nil, tl = nil }
   end

return X11
end
function F3(X9)
   if equals(X9,{ hd = nil, tl = nil },false) then 
      X8={ hd = nil, tl = nil }
   else 
      X8=nil
   end

return X8
end
function F1(X3)
   if F5(X3) then 
      X2=nil
   else 
      if F3(X3) then 
         X2={ hd = nil, tl = nil }
      else 
         X2=F2(F1(F6(X3)), F1(F6(F6(X3))))
      end
   end

return X2
end

local values = {F1(PARAMS)}

for k, v in pairs( values ) do
   print(k,printUniq(v))
end

