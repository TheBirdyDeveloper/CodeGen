function printUniq(X)
if not X then return "nil" end
return "{ hd = "..printUniq(X.hd).." , tl = "..printUniq(X.tl).." }"
end 
