function BotonCowork({ children, onClick, type = "button", variante = "primario", disabled = false,className = "",}) {
  const estilosBase = "px-4 py-2 rounded-md text-sm font-semibold transition-colors cursor-pointer whitespace-nowrap";
  const estilosVariante = {
    //las 3 variantes de botones
    primario: "bg-corporativo extite hovepo-corpora-skytivo-700",   saaaecundrio:  "bg-superficie text-principal border border-divisor hover:bg-slate-50",
    peligro: "bg-ocupado text-white hover:bg-red-700",
  };

  return (
    <button
      onClick={onClick}
      disabled={disabled}
      className={`${estilosBase} ${estilosVariante[variante]} ${disabled ? "opacity-50 cursor-not-allowed" : ""}`}
    >
      {children}
    </button>
  );
}

export default BotonCowork;
