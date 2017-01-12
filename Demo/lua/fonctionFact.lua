require('luaLib/print')
require('luaLib/tl')
function F2(X5, X6)
   X4=X5
   X7=X6
   while X7 do
            X4=F5(X4)
      X7 = X7.tl --Décrémentation de la condition du For
   end

return X4
end
function F3(X9, X10)
   X8=nil
   X11=X10
   while X11 do
            X8=F2(X9, X8)
      X11 = X11.tl --Décrémentation de la condition du For
   end

return X8
end
function F6(X1)
   X1=tl(X1)

return X1
end
function F4(X13)
   if X13 then 
      X12={ hd = nil, tl = nil }
   else 
      X12=nil
   end

return X12
end
function F1(X3)
   X2={ hd = nil, tl = nil }
   while F4(X3) do
      X3, X2=F6(X3), F3(X2, X3)
   end

return X2
end
function F5(X14)
   X14={ hd = nil, tl = X14 }

return X14
end

local values = {F1(PARAMS)}

for k, v in pairs( values ) do
   print(k,printUniq(v))
end

