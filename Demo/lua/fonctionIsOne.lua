require('luaLib/print')
require('luaLib/equals')
function F1(X2)
   if equals(X2,{ hd = nil, tl = nil },false) then 
      X1={ hd = nil, tl = nil }
   else 
      X1=nil
   end

return X1
end

local values = {F1(PARAMS)}

for k, v in pairs( values ) do
   print(k,printUniq(v))
end

