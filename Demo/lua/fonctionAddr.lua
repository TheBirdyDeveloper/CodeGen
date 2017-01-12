require('luaLib/print')
require('luaLib/tl')
function F1(X2, X3)
   if F3(X3) then 
      X1=X2
   else 
      X1=F2(F1(X2, F4(X3)))
   end

return X1
end
function F4(X7)
   X7=tl(X7)

return X7
end
function F2(X4)
   X4={ hd = nil, tl = X4 }

return X4
end
function F3(X6)
   if X6 then 
      X5=nil
   else 
      X5={ hd = nil, tl = nil }
   end

return X5
end

local values = {F1(PARAMS)}

for k, v in pairs( values ) do
   print(k,printUniq(v))
end

