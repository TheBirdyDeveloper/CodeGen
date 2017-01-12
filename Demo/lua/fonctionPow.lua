require('luaLib/print')
function F2(X6, X7)
   X5=X6
   X8=X7
   while X8 do
            X5=F4(X5)
      X8 = X8.tl --Décrémentation de la condition du For
   end

return X5
end
function F4(X13)
   X13={ hd = nil, tl = X13 }

return X13
end
function F3(X10, X11)
   X9=nil
   X12=X11
   while X12 do
            X9=F2(X10, X9)
      X12 = X12.tl --Décrémentation de la condition du For
   end

return X9
end
function F1(X2, X3)
   X1={ hd = nil, tl = nil }
   X4=X3
   while X4 do
            X1=F3(X2, X1)
      X4 = X4.tl --Décrémentation de la condition du For
   end

return X1
end

local values = {F1(PARAMS)}

for k, v in pairs( values ) do
   print(k,printUniq(v))
end

