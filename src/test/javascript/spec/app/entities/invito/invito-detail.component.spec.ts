import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { InvitoDetailComponent } from 'app/entities/invito/invito-detail.component';
import { Invito } from 'app/shared/model/invito.model';

describe('Component Tests', () => {
  describe('Invito Management Detail Component', () => {
    let comp: InvitoDetailComponent;
    let fixture: ComponentFixture<InvitoDetailComponent>;
    const route = ({ data: of({ invito: new Invito(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InvitoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InvitoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load invito on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.invito).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
