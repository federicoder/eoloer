import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { RisorseDisponibiliComponent } from 'app/entities/risorse-disponibili/risorse-disponibili.component';
import { RisorseDisponibiliService } from 'app/entities/risorse-disponibili/risorse-disponibili.service';
import { RisorseDisponibili } from 'app/shared/model/risorse-disponibili.model';

describe('Component Tests', () => {
  describe('RisorseDisponibili Management Component', () => {
    let comp: RisorseDisponibiliComponent;
    let fixture: ComponentFixture<RisorseDisponibiliComponent>;
    let service: RisorseDisponibiliService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [RisorseDisponibiliComponent],
      })
        .overrideTemplate(RisorseDisponibiliComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RisorseDisponibiliComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RisorseDisponibiliService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RisorseDisponibili(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.risorseDisponibilis && comp.risorseDisponibilis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
