/**
 * Agrupa las definiciones de <b>puertos</b> del dominio.
 *
 * <p>Los puertos son interfaces que permiten desacoplar el núcleo de
 * negocio de los mecanismos de entrada/salida, siguiendo la Arquitectura
 * Hexagonal (Ports &amp; Adapters).</p>
 *
 * <p>Se organiza en dos subpaquetes:</p>
 * <ul>
 *     <li>{@code in} – puertos de entrada (casos de uso).</li>
 *     <li>{@code out} – puertos de salida (dependencias externas).</li>
 * </ul>
 */
package com.elena.companies_crud.domain.ports;