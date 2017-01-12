require('luaLib/print')
require('luaLib/tl')
function F6(X1)
   X1=tl(X1)

return X1
end
function F1(X4, X5)
   X2, X3=F2(X4, X5), nil
   if F3(X3) then 
      X6=F1(X5, X3)
   else 
      X6=X5
   end

return X6
end
function F2(X10, X11)
   X7, X8=nil, X10
   X9=F5(X8, X11)
   while F3(X9) do
      X7, X8=F4(X7), X9
   end

return X8,X7
end
function F4(X14)
   X14={ hd = nil, tl = X14 }

return X14
end
function F5(X16, X17)
   X15=X16
   X18=X17
   while X18 do
            X15=F6(X15)
      X18 = X18.tl --Décrémentation de la condition du For
   end

return X15
end
function F3(X13)
   if X13 then 
      X12={ hd = nil, tl = nil }
   else 
      X12=nil
   end

return X12
end

local values = {F1(PARAMS)}

for k, v in pairs( values ) do
   print(k,printUniq(v))
end

