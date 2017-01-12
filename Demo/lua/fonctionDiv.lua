require('luaLib/print')
require('luaLib/tl')
function F1(X4, X5)
   X1, X2=nil, X4
   X3=F4(X2, X5)
   while F2(X3) do
      X1, X2=F3(X1), X3
   end

return X1,X2
end
function F4(X10, X11)
   X9=X10
   X12=X11
   while X12 do
            X9=F5(X9)
      X12 = X12.tl --Décrémentation de la condition du For
   end

return X9
end
function F5(X13)
   X13=tl(X13)

return X13
end
function F2(X7)
   if X7 then 
      X6={ hd = nil, tl = nil }
   else 
      X6=nil
   end

return X6
end
function F3(X8)
   X8={ hd = nil, tl = X8 }

return X8
end

local values = {F1(PARAMS)}

for k, v in pairs( values ) do
   print(k,printUniq(v))
end

